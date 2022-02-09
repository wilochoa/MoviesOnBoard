package com.apponboard.moviesonboard.view.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.apponboard.moviesonboard.model.remote.NetworkUrl
import com.apponboard.moviesonboard.model.remote.NetworkUrl.Companion.ORIGINAL_SIZE
import com.apponboard.moviesonboard.model.remote.NetworkUrl.Companion.SMALL_SIZE
import com.squareup.picasso.Picasso

class ImgSetter {
    companion object {
        @JvmStatic
        @BindingAdapter("android:src_image")
        fun setImageUrl(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get()
                    .load(NetworkUrl.BASE_URL_IMG+ SMALL_SIZE+url)
                    .into(imageView);
            }
        }
        @JvmStatic
        @BindingAdapter("android:src_image_detail")
        fun setImageUrlDetail(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get()
                    .load(NetworkUrl.BASE_URL_IMG+ORIGINAL_SIZE+url)
                    .into(imageView);
            }
        }
    }
}