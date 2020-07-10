package com.gosemathraj.foodzilla.ui.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.gosemathraj.foodzilla.R

@BindingAdapter("setImageUrl")
fun setImageUrl(imageView: AppCompatImageView, url : String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url) // or URI/path
            .placeholder(R.drawable.ic_launcher_background)
            .priority(Priority.IMMEDIATE)
            .error(R.drawable.ic_launcher_background)
            .into(imageView);
    }
}