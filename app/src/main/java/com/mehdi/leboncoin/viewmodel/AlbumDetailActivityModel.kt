package com.mehdi.leboncoin.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import com.mehdi.leboncoin.db.AlbumDatabase
import com.mehdi.leboncoin.entities.AlbumEntity
import com.mehdi.leboncoin.repository.AlbumRepository
import com.squareup.picasso.Picasso

/**
 * Created by mehdi on 2020-06-16.
 */

class AlbumDetailActivityViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    private val repository: AlbumRepository

    lateinit var albums : LiveData<List<AlbumEntity>>

    init {
        val albumDao = AlbumDatabase.getDatabase(application).albumDao()
        repository = AlbumRepository(albumDao)
    }

    fun getAlbumsById(albumId : Int) {
        albums = repository.getAlbumsById(albumId)
    }

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