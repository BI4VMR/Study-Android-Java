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
    private AudioAttributes attributes;
    private AudioFocusRequest longRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btRequestFocus = findViewById(R.id.btRequestFocus);
        Button btAbandon = findViewById(R.id.btAbandon);

        // 获取音频管理器
        manager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // 音频属性，影响系统是否允许当前应用获取音频焦点，各音源的优先级由Framework决定。
        attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        // 获取音频焦点按钮
        btRequestFocus.setOnClickListener(v -> {
            // 构建请求实例，请求获得持久音频焦点。
            longRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    // 设置音频属性，是可选参数，默认值为Usage为USAGE_MEDIA的实例。
                    .setAudioAttributes(attributes)
                    // 设置音频焦点改变监听器
                    .setOnAudioFocusChangeListener(new MyAudioFocusChangeListener())
                    .build();

            // 使用音频管理器请求音频焦点
            int result = manager.requestAudioFocus(longRequest);
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
        btAbandon.setOnClickListener(v -> {
            if (longRequest != null) {
                manager.abandonAudioFocusRequest(longRequest);
                Log.d("myapp", "成功释放音频焦点");
            }
        });
    }

    // “音频焦点改变”监听器
    class MyAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {

        /**
         * 音频焦点改变事件
         *
         * @param focusChange 表示当前程序的焦点状态
         */
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    /*
                     * 其它程序获得临时焦点并释放后，本程序进入该状态。
                     *
                     * 此时可以继续进行播放。
                     * 注意：本程序首次请求焦点成功后不会执行本回调。
                     */
                    Log.d("myapp", "重新获得持久音频焦点");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    /*
                     * 持久失去音频焦点，其它程序成功请求持久焦点后，本程序进入该状态。
                     *
                     * 由于其它程序可能长时间播放，此时应当记录播放进度，释放资源，
                     * 直到用户对本程序发出继续播放的指令。
                     */
                    Log.d("myapp", "持久失去音频焦点");
                    /*
                     * 释放音频焦点
                     *
                     * Android 9.0以下系统中失去持久音频焦点后，一旦其它程序释放持久焦点，本程序会再次收到
                     * "AUDIOFOCUS_GAIN"事件，继续播放。
                     * 为了防止对用户造成困扰，持久失去焦点后此处自主释放音频焦点，从此不再接收任何焦点改变事件。
                     */
                    manager.abandonAudioFocusRequest(longRequest);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    /*
                     * 暂时失去音频焦点，其它程序成功请求临时焦点后，本程序进入该状态。
                     *
                     * 由于其它程序播放时间较短，此时应当暂停播放，以便收到恢复消息时快速恢复播放。
                     */
                    Log.d("myapp", "暂时失去音频焦点");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    /*
                     * 暂时失去音频焦点，其它程序成功请求"Duck"方式的临时焦点后，本程序进入该状态。
                     *
                     * Android 8.0及更高版本的系统，会自动降低本程序的媒体音量，开发者不需要作任何操作。
                     * Android 8.0以下版本的系统，需要自行实现降低音量的功能。
                     */
                    Log.d("myapp", "暂时失去音频焦点，且本程序应当降低音量播放。");
                    break;
            }
        }
    }
}