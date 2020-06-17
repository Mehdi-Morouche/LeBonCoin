package com.mehdi.leboncoin.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import com.squareup.picasso.Picasso

/**
 * Created by mehdi on 2020-06-16.
 */

class AlbumItemActivityViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    companion object{
        @JvmStatic
        @BindingAdapter("app:load_image")
        fun setImageUrl(view: ImageView, imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }
}