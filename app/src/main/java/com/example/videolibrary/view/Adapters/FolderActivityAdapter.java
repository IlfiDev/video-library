package com.example.videolibrary.view.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videolibrary.R;
import com.example.videolibrary.model.Video;

import java.util.List;

public class FolderActivityAdapter extends BaseAdapter {
    private List<Video> list;
    private LayoutInflater layoutInflater;
    private int resourceLayout;

    public FolderActivityAdapter(Context context, int resource, List<Video> list){
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceLayout = resource;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView = view;
        if(newView == null){
            newView = layoutInflater.inflate(resourceLayout, null);
        }
        TextView name = (TextView) newView.findViewById(R.id.video_text);
        ImageView image = (ImageView) newView.findViewById(R.id.video_thumbnail);
        name.setText(list.get(i).getName());
        image.setImageBitmap(list.get(i).getThumbnail());
        return newView;

    }
}
