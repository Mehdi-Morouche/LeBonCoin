package com.mehdi.leboncoin

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mehdi.leboncoin.dao.AlbumDao
import com.mehdi.leboncoin.db.AlbumDatabase
import com.mehdi.leboncoin.entities.AlbumEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by mehdi on 2020-06-17.
 */

@RunWith(AndroidJUnit4::class)
class ListOfAlbumIdTest{
    private lateinit var albumDatabase: AlbumDatabase
    private lateinit var albumDao: AlbumDao

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        albumDatabase = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java).build()

        albumDao = albumDatabase.albumDao()
    }

    @After
    fun finish() {
        albumDatabase.close()
    }

    @Test
    fun InsertAllInDatabase() = runBlocking {

        val album1 = AlbumEntity(1, 1, "title", "url", "thumbnail")
        val album2 = AlbumEntity(2, 2, "title2", "url2", "thumbnail2")
        val album3 = AlbumEntity(1, 3, "title3", "url3", "thumbnail3")
        val album4 = AlbumEntity(3, 4, "title4", "url4", "thumbnail4")

        var listAlbums : MutableList<AlbumEntity> = mutableListOf()

        var albumsSize = mutableListOf<Int>()

        albumDao.insertAll(listAlbums)

        listAlbums.add(album1)
        listAlbums.add(album2)
        listAlbums.add(album3)
        listAlbums.add(album4)

        albumDao.insertAll(listAlbums)

        val selection = albumDao.getAlbums().blockingObserve()

        ViewMatchers.assertThat(listAlbums, CoreMatchers.`is`(selection))

        var cpt = 0

        listAlbums.sortBy { it.albumId }

        for (album in listAlbums) {
            if (albumsSize.size == 0) {
                albumsSize.add(album.albumId!!)
            }

            if ((album.albumId != albumsSize[cpt])) {
                albumsSize.add(album.albumId!!)
                cpt++
            }
        }

        assertEquals(albumsSize.size, 3)
    }

    @Rule
    @JvmField val rule = InstantTaskExecutorRule()
    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }

        observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}