package com.application.sharechat.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.sharechat.R;
import com.bumptech.glide.Glide;

import java.net.URLConnection;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {
    private Context context;
    private List<String> list;

    public MediaAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.images_layout, parent, false);
        return new MediaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaAdapter.ViewHolder holder, int position) {
        String url = list.get(position);
      if (url.contains("jpg") || url.contains("png") || url.contains("jpeg")) {
            holder.videoView.setVisibility(View.GONE);
            holder.layout.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(url).into(holder.imageView);
       } else {
            MediaController mediaController= new MediaController(context);
            mediaController.setAnchorView(holder.videoView);
            mediaController.setVisibility(View.GONE);
           holder.videoView.setVisibility(View.VISIBLE);
           holder.layout.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
            holder.videoView.setMediaController(mediaController);
            holder.videoView.setVideoURI(Uri.parse(url));
            holder.videoView.requestFocus();
            holder.videoView.start();
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        VideoView videoView;
        RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.images);
            videoView = itemView.findViewById(R.id.videos);
            layout = itemView.findViewById(R.id.relative);
        }
    }
}
