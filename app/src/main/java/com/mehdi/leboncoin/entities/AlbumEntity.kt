package com.mehdi.leboncoin.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Created by mehdi on 2020-06-15.
 */

@Serializable
@Entity(tableName = "album")
data class AlbumEntity (
    @field:SerializedName("albumId")
    @ColumnInfo(name = "albumId") var albumId: Int? = null,
    @PrimaryKey
    @field:SerializedName("id")
    @ColumnInfo(name = "id") var id: Int? = null,
    @field:SerializedName("title")
    @ColumnInfo(name = "title") var title: String? = null,
    @field:SerializedName("url")
    @ColumnInfo(name = "url") var url: String? = null,
    @field:SerializedName("thumbnailUrl")
    @ColumnInfo(name = "thumbnailUrl") var thumbnailUrl: String? = null
)