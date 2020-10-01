package com.example.moviereviewandratingapp;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;


public class BindingUtils {

    @BindingAdapter(value = {"url"})
    public static void loadThumbnailImage(ImageView imageView, String url) { //loading image
        Context context = imageView.getContext(); // got image
        // Setup Placeholder
        Drawable drawablePlaceholder = context.getDrawable(R.drawable.ic_whatshot_black_24dp); // replace the default whatshot image with the movie wallpaper

        // Load Image or Placeholder
        Glide.with(imageView)
                .load(url)
                .placeholder(drawablePlaceholder)
                .into(imageView);
    }
}