package com.mehdi.leboncoin.repository

import androidx.lifecycle.LiveData
import com.mehdi.leboncoin.dao.AlbumDao
import com.mehdi.leboncoin.entities.AlbumEntity

/**
 * Created by mehdi on 2020-06-15.
 */

class AlbumRepository(private val albumDao: AlbumDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val albums : LiveData<List<AlbumEntity>> = albumDao.getAlbums()

    suspend fun insertAll(album: List<AlbumEntity>) {
        albumDao.insertAll(album)
    }

    fun getAlbumsById(albumId : Int) : LiveData<List<AlbumEntity>> {
        return albumDao.getAlbumsById(albumId)
    }

}