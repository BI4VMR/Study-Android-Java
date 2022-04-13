package net.bi4vmr.study.media_audio_audiofocus;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 音频管理器
    private AudioManager manager;
    private AudioFocusRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btRequestFocus = findViewById(R.id.btRequestFocus);
        Button btAbandon = findViewById(R.id.btAbandon);

        // 获取音频管理器
        manager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // 获取音频焦点按钮
        btRequestFocus.setOnClickListener(v -> {
            // 音频属性，影响系统是否允许当前应用获取音频焦点。
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            // 构建请求实例，请求持久获得音频焦点。
            request = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    // 设置音频属性，是可选参数，默认值为Usage为USAGE_MEDIA的实例。
                    .setAudioAttributes(attributes)
                    // 设置音频焦点改变监听器
                    .setOnAudioFocusChangeListener(focusChange -> {
                        // "focusChange"的值表示当前程序的焦点状态
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN:
                                Log.d("myapp", "重新获取持久音频焦点");
                                break;
                            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                                Log.d("myapp", "重新获取临时音频焦点");
                                break;
                            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                                Log.d("myapp", "重新获取临时音频焦点，且其它音频流的音量降低。");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                Log.d("myapp", "持久失去音频焦点");
                                // 停止放音操作
                                Log.i("myapp", "停止放音");
                                // 释放音频焦点
                                manager.abandonAudioFocusRequest(request);
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                Log.d("myapp", "暂时失去音频焦点");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                Log.d("myapp", "暂时失去音频焦点，且本程序应当降低音量播放。");
                                break;
                        }
                    })
                    .build();

            // 使用音频管理器请求音频焦点
            int result = manager.requestAudioFocus(request);
            // 根据返回值执行进一步操作
            switch (result) {
                case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                    Log.d("myapp", "音频焦点请求成功");
                    break;
                case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                    Log.d("myapp", "音频焦点请求成功（延迟）");
                    break;
                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                    // “正在通话”等情况会导致音频焦点请求失败
                    Log.d("myapp", "音频焦点请求失败");
                    break;
            }
        });

        // 放弃音频焦点按钮
        btAbandon.setOnClickListener(v -> manager.abandonAudioFocusRequest(request));
    }
}