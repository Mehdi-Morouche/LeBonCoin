package com.mehdi.leboncoin

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mehdi.leboncoin.dao.AlbumDao
import com.mehdi.leboncoin.db.AlbumDatabase
import com.mehdi.leboncoin.entities.AlbumEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
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
class AlbumDaoTest{
    private lateinit var albumDatabase: AlbumDatabase
    private lateinit var albumDao: AlbumDao

    @Before
    fun init() {
        val context = getApplicationContext<Context>()
        albumDatabase = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java)
            .build()

        albumDao = albumDatabase.albumDao()
    }

    @After
    fun finish() {
        albumDatabase.close()
    }

    @Test
    fun InsertAllInDatabase() = runBlocking {

        val album1 = AlbumEntity(1, 1, "title", "url", "thumbnail")
        val album2 = AlbumEntity(1, 2, "title2", "url2", "thumbnail2")

        var listAlbums : MutableList<AlbumEntity> = mutableListOf()
        listAlbums.add(album2)

        var listAlbums2 : MutableList<AlbumEntity> = mutableListOf()
        listAlbums2.add(album1)
        listAlbums2.add(album2)

        albumDao.insertAll(listAlbums)

        val selection = albumDao.getAlbums().blockingObserve()

        assertEquals(listAlbums, selection)
        //assertEquals(listAlbums2, selection)
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