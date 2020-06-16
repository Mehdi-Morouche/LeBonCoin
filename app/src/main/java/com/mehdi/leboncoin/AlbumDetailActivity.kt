package com.mehdi.leboncoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mehdi.leboncoin.adapter.AlbumAdapter
import com.mehdi.leboncoin.databinding.ActivityAlbumDetailBinding
import com.mehdi.leboncoin.viewmodel.AlbumDetailActivityViewModel


/**
 * Created by mehdi on 2020-06-16.
 */

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var mModel: AlbumDetailActivityViewModel

    lateinit var dataBinding: ActivityAlbumDetailBinding

    var adapter = AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mModel = ViewModelProviders.of(this).get(AlbumDetailActivityViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView<ActivityAlbumDetailBinding>(this, R.layout.activity_album_detail).apply {
            setLifecycleOwner(this@AlbumDetailActivity)
            lifecycle.addObserver(mModel)
        }

        mModel.getAlbumsById(intent?.getStringExtra(ARG_ALBUM_ID)!!.toInt())

        mModel.albums.observe(this, Observer { albums ->
            dataBinding.recyclerAlbum?.adapter = adapter
            adapter.setData(dataList = albums)
        })
    }

    companion object {
        const val ARG_ALBUM_ID = "album_id"
    }
}
