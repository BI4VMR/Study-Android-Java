package net.bi4vmr.study.media_demo_musicplayer.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;

import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.manager.MediaNotificationManager;
import net.bi4vmr.study.media_demo_musicplayer.util.MusicUtil;

import java.io.IOException;
import java.util.List;

/**
 * Name          : MusicService
 * Author        : 詹屹罡
 * Email         : yigangzhan@pateo.com.cn
 * Date          : 2022-04-18 11:23
 * Description   : 音乐播放服务
 */
public class MusicService extends MediaBrowserServiceCompat {

    private static final String TAG_APP = MusicApp.TAG;
    private static final String TAG_CLS = MusicService.class.getSimpleName();

    // 全局环境
    private Context mContext;

    private MediaSessionCompat mediaSession;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG_APP, TAG_CLS + ":onCreate()");

        mContext = getApplicationContext();

        // 创建媒体会话实例
        mediaSession = new MediaSessionCompat(this, TAG_CLS);
        // 设置MediaController的回调方法，处理媒体控制指令。
        mediaSession.setCallback(new MyMediaControllerCallback());
        // 激活会话，使得会话控制器能够接收指令。
        mediaSession.setActive(true);
        /*
         * 获取会话令牌，并将令牌绑定到此服务。
         *
         * 设置令牌后，客户端的ConnectionCallback将被调用。
         */
        MediaSessionCompat.Token token = mediaSession.getSessionToken();
        setSessionToken(token);

        // 创建媒体播放器实例
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            /**
             * MediaPlayer准备就绪的回调
             *
             * @param mp MediaPlayer实例
             */
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 开始放音
                mp.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            /**
             * MediaPlayer播放完成的回调
             *
             * @param mp MediaPlayer实例
             */
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
    }

    /**
     * 接受启动服务的指令，转为前台服务，防止界面在后台时服务被系统终止。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG_APP, TAG_CLS + ":onStartCommand()");

        // 获取通知管理器实例
        MediaNotificationManager mnManager = MediaNotificationManager.getInstance(mContext);
        // 获取通知实例
        Notification notification = mnManager.createNotification();
        // 启动前台服务，并绑定通知。
        startForeground(100, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Name        : onGetRoot()
     * Description : 本服务连接成功时执行该回调方法。
     *
     * @param clientPackageName
     * @param clientUid
     * @param rootHints
     * @return 返回不为Null时表示连接成功
     */
    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        Log.d(TAG_APP, TAG_CLS + ":onGetRoot()");
        return new BrowserRoot(TAG_CLS, null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        Log.d(TAG_APP, TAG_CLS + ":onLoadChildren()");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG_APP, TAG_CLS + ":onDestroy()");

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mediaSession != null) {
            mediaSession.release();
            mediaSession = null;
        }
        super.onDestroy();
    }

    /*
     * 播放回调，处理控制器的指令。
     */
    class MyMediaControllerCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            Log.d(TAG_APP, TAG_CLS + ":onPlay");

            // 继续MediaPlayer的播放动作
            mediaPlayer.start();
            // 设置MediaSession的状态为暂停
            PlaybackStateCompat playbackState = new PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                    .build();
            mediaSession.setPlaybackState(playbackState);
        }

        /**
         * 控制器端调用"playFromUri()"方法时触发此回调
         *
         * @param uri    "playFromUri()"传入的参数
         * @param extras "playFromUri()"传入的参数
         */
        @Override
        public void onPlayFromUri(Uri uri, Bundle extras) {
            Log.d(TAG_APP, TAG_CLS + ":onPlayFromUri()");

            // 重置MediaPlayer状态
            mediaPlayer.reset();
            // 设置新的数据源，并准备播放。
            try {
                mediaPlayer.setDataSource(MusicService.this, uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.prepareAsync();

            // 设置MediaSession的播放状态
            PlaybackStateCompat playbackState = new PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                    .setActions(PlaybackStateCompat.ACTION_PLAY)
                    .build();
            mediaSession.setPlaybackState(playbackState);

            if (extras == null) {
                return;
            }
            String title = extras.getString("Title");
            String artist = extras.getString("Artist");
            Bitmap cover = MusicUtil.getMusicCover(uri.toString(), mContext);
            // 设置MediaSession的媒体属性
            MediaMetadataCompat metadata = new MediaMetadataCompat.Builder()
                    .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                    .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                    .putBitmap(MediaMetadataCompat.METADATA_KEY_ART, cover)
                    .build();
            mediaSession.setMetadata(metadata);
        }

        @Override
        public void onPause() {
            Log.d(TAG_APP, TAG_CLS + ":onPause");

            // 暂停MediaPlayer
            mediaPlayer.pause();
            // 设置MediaSession的状态为暂停
            PlaybackStateCompat playbackState = new PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PAUSED, 0, 1.0f)
                    .build();
            mediaSession.setPlaybackState(playbackState);
        }
    }
}
