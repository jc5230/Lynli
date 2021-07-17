package com.innojane.lynli;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.ViewHolder> {

    private List<String> avatarURLs;

    public AvatarAdapter() {
        this.avatarURLs = new ArrayList<>();
    }

    public AvatarAdapter(List<String> avatarURLs) {
        this.avatarURLs = avatarURLs;
    }

    public void addAvatar(String avatarURL) {
        this.avatarURLs.add(avatarURL);
    }

    public String getAvatarURL(int position) {
        return avatarURLs.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            imageView = (ImageView) view.findViewById(R.id.imageview_avatar);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void bindImageView(String avatarURL) {
            Glide.with(imageView.getContext())
                    .load(avatarURL)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(imageView);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.image_avatar, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bindImageView(getAvatarURL(position));
    }

    @Override
    public int getItemCount() {
        return avatarURLs.size();
    }

}
