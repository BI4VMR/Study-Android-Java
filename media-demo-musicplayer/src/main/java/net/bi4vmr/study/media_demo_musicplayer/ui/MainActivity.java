package net.bi4vmr.study.media_demo_musicplayer.ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.media_demo_musicplayer.R;
import net.bi4vmr.study.media_demo_musicplayer.adapter.MusicListAdapter;
import net.bi4vmr.study.media_demo_musicplayer.bean.MusicVO;
import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.databinding.ActivityMainBinding;
import net.bi4vmr.study.media_demo_musicplayer.manager.MusicDataManager;
import net.bi4vmr.study.media_demo_musicplayer.service.MusicService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG_APP = MusicApp.TAG;
    private static final String TAG_CLS = "MainActivity";

    private ActivityMainBinding binding;
    private MusicListAdapter adapter;

    private MusicDataManager musicManager;

    private MediaBrowserCompat mediaBrowser;
    private MediaControllerCompat mediaController;

    @SuppressLint("SwitchIntDef")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG_APP, TAG_CLS + ":onCreate()");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 初始化音乐列表控件
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvMusic.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rvMusic.addItemDecoration(itemDecoration);
        adapter = new MusicListAdapter(new ArrayList<>(), this);
        binding.rvMusic.setAdapter(adapter);
        adapter.setOnItemClickListener(new MusicListAdapter.OnItemClickListener() {
            // 列表项的点击事件
            @Override
            public void onItemClick(int position, MusicVO vo) {
                Log.d(TAG_APP, TAG_CLS + ":点击表项：" + vo.toString());
                //
                Uri itemURI = Uri.parse(vo.getUri());
                // 获取额外数据
                Bundle bundle = new Bundle();
                bundle.putString("Title", vo.getTitle());
                bundle.putString("Artist", vo.getArtist());
                mediaController.getTransportControls().playFromUri(itemURI, bundle);
            }

            // 列表项的长按事件
            @Override
            public void onItemLongClick(int position, MusicVO vo) {
                Log.d(TAG_APP, TAG_CLS + ":长按表项：" + vo.toString());
            }
        });

        // 播放按钮点击事件
        binding.layoutMusicbar.ibPlay.setOnClickListener(v -> {
            int status = mediaController.getPlaybackState().getState();
            switch (status) {
                case PlaybackStateCompat.STATE_PLAYING:
                    mediaController.getTransportControls().pause();
                    break;
                case PlaybackStateCompat.STATE_PAUSED:
                    mediaController.getTransportControls().play();
                    break;
                default:
                    break;
            }
        });

        // 获取音乐管理器实例
        musicManager = MusicDataManager.getInstance(this);

        // 开启服务
        Intent intent = new Intent(this, MusicService.class);
        startForegroundService(intent);

        // 创建MediaBrowser
        mediaBrowser = new MediaBrowserCompat(this,
                new ComponentName(this, MusicService.class),
                new MyMediaBrowserConnectionCallback(),
                null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_APP, TAG_CLS + ":onResume()");

        // 连接音乐播放服务
        mediaBrowser.connect();

        // 获取本地音乐数据，并加载至列表。
        List<MusicVO> datas = musicManager.getAudioData();
        if (datas != null) {
            adapter.reloadItem(datas);
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG_APP, TAG_CLS + ":onStop()");

        if (mediaBrowser != null) {
            mediaBrowser.disconnect();
        }

        super.onStop();
    }

    /**
     * 音频服务连接回调
     */
    class MyMediaBrowserConnectionCallback extends MediaBrowserCompat.ConnectionCallback {

        @Override
        public void onConnected() {
            Log.d(TAG_APP, TAG_CLS + ":onConnected()");

            // 获取令牌
            MediaSessionCompat.Token token = mediaBrowser.getSessionToken();
            // 通过令牌获取媒体控制器
            mediaController = new MediaControllerCompat(getApplicationContext(), token);
            // 注册媒体控制器回调，处理播放状态变更消息。
            mediaController.registerCallback(new MyMediaControllerCallback());

            mediaController.getMetadata();
        }

        @Override
        public void onConnectionSuspended() {
            Log.d(TAG_APP, TAG_CLS + ":onConnectionSuspended()");
        }

        @Override
        public void onConnectionFailed() {
            Log.d(TAG_APP, TAG_CLS + ":onConnectionFailed()");
        }
    }

    /**
     * 媒体控制器的回调类
     */
    class MyMediaControllerCallback extends MediaControllerCompat.Callback {

        /**
         * 播放状态改变回调
         *
         * @param state 播放状态
         */
        @SuppressLint("SwitchIntDef")
        @Override
        public void onPlaybackStateChanged(@Nullable PlaybackStateCompat state) {
            Log.d(TAG_APP, TAG_CLS + ":onPlaybackStateChanged()");
            if (state == null) {
                return;
            }

            int status = state.getState();
            switch (status) {
                case PlaybackStateCompat.STATE_NONE:
                    Log.i(TAG_APP, TAG_CLS + ":STATE_NONE");
                    break;
                case PlaybackStateCompat.STATE_PLAYING:
                    binding.layoutMusicbar.ibPlay.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_playback_pause));
                    break;
                case PlaybackStateCompat.STATE_PAUSED:
                    binding.layoutMusicbar.ibPlay.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_playback_play));
                    break;
            }
        }

        /**
         * 媒体元数据改变回调
         *
         * @param metadata 媒体元数据
         */
        @Override
        public void onMetadataChanged(@Nullable MediaMetadataCompat metadata) {
            Log.d(TAG_APP, TAG_CLS + ":onMetadataChanged()");

            // 如果媒体元数据为Null，则重置文本，退出当前方法。
            if (metadata == null) {
                binding.layoutMusicbar.tvTitle.setText("无曲目信息");
                binding.layoutMusicbar.tvArtist.setText("-");
                binding.layoutMusicbar.ivCover.setImageResource(R.drawable.ic_playback_unknown_cover);
                return;
            }

            // 媒体元数据不为Null，则读取数据，并设置到文本上。
            String title = metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
            String artist = metadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
            Bitmap cover = metadata.getBitmap(MediaMetadataCompat.METADATA_KEY_ART);
            binding.layoutMusicbar.tvTitle.setText(title);
            binding.layoutMusicbar.tvArtist.setText(artist);
            binding.layoutMusicbar.ivCover.setImageBitmap(cover);
        }
    }
}