package com.example.videolibrary.model;

// Need the READ_EXTERNAL_STORAGE permission if accessing video files that your
// app didn't create.

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

// Container for information about each video.
public class Video {
    private final Uri uri;
    private final String name;
    private final int duration;
    private final int size;
    private final Bitmap thumbnail;

    public Video(Uri uri, String name, int duration, int size, Bitmap thumbnail) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.thumbnail = thumbnail;
    }
    public String getName(){
        return name;
    }
    public Bitmap getThumbnail(){
        return thumbnail;
    }
}

