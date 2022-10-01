package com.example.videolibrary.viewmodel;

import android.app.Application;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.videolibrary.model.Video;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VideosViewModel extends AndroidViewModel implements Observable {
    public VideosViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
    List<Video> videoList = new ArrayList<Video>();
    private MutableLiveData<List<Video>> videos;
    public MutableLiveData<List<Video>> getVideos(){
        if (videos == null) {
             videos = new MutableLiveData<List<Video>>();
            updateVideoList();
        }
        return videos;

    }

    public void updateVideoList(){


        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.VideoColumns.DATA};
        Cursor cursor = getApplication().getApplicationContext().getContentResolver().query(uri,
                projection,
                null,
                null,
                null);
        ArrayList<String> pathArrList = new ArrayList<>();
        if(cursor != null){
            while (cursor.moveToNext()){
                pathArrList.add(cursor.getString(0));
            }
            cursor.close();
        }
        Log.e("all path", pathArrList.toString());
    }



}
