package com.example.videolibrary.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    private String name;
    private List<Video> videos = new ArrayList<>();

    public Folder(String name, List<Video> videos) {
        this.name = name;
        this.videos = videos;
    }

    public int getAmountOfItems() {
        return videos.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public void addVideo(Video video){
        videos.add(video);
    }
}
