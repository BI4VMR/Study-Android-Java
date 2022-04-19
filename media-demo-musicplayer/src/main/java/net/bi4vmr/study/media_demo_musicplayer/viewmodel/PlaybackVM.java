package net.bi4vmr.study.media_demo_musicplayer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Name          : PlaybackVM
 * Author        : BI4VMR
 * Date          : 2022-04-19 11:40
 * Description   : 音频回放ViewModel
 */
public class PlaybackVM extends AndroidViewModel {

    private final Context mContext;

    public PlaybackVM(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    LiveData<MediaMetadataCompat> mediaMetadata = new MutableLiveData<>();
    LiveData<MediaMetadataCompat> observeMediaMetadata = mediaMetadata;
}
