package com.mehdi.leboncoin.api

import com.mehdi.leboncoin.entities.AlbumEntity
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by mehdi on 2020-06-15.
 */

interface AlbumService {

    companion object {
        const val ENDPOINT = "https://static.leboncoin.fr/img/shared/"
    }

    @GET("technical-test.json")
    suspend fun getAlbums(): Response<List<AlbumEntity>>

}