package com.example.videolibrary.model;

// Need the READ_EXTERNAL_STORAGE permission if accessing video files that your
// app didn't create.

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

// Container for information about each video.
public class Video {
    private final Uri uri;
    private final String name;
    private final int duration;
    private final int size;

    public Video(Uri uri, String name, int duration, int size) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;
    }
    public String getName(){
        return name;
    }
}

