package com.mehdi.leboncoin.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mehdi.leboncoin.entities.AlbumEntity

/**
 * Created by mehdi on 2020-06-15.
 */

@Dao
interface AlbumDao {

    @Query("SELECT * from album")
    fun getAlbums(): LiveData<List<AlbumEntity>>

    @Query("SELECT * from album WHERE albumId = :albumId")
    fun getAlbumsById(albumId : Int): LiveData<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<AlbumEntity>)

}