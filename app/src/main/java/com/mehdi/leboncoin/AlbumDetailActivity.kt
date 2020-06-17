package com.mehdi.leboncoin

import android.os.Bundle
import android.view.MenuItem
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

        val albumId = intent?.getStringExtra(ARG_ALBUM_ID)!!.toInt()

        // Show the title of the album
        supportActionBar?.setTitle("Album $albumId")

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mModel = ViewModelProviders.of(this).get(AlbumDetailActivityViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView<ActivityAlbumDetailBinding>(this, R.layout.activity_album_detail).apply {
            setLifecycleOwner(this@AlbumDetailActivity)
            lifecycle.addObserver(mModel)
        }

        mModel.getAlbumsById(albumId)

        mModel.albums.observe(this, Observer { albums ->
            dataBinding.recyclerAlbum?.adapter = adapter
            adapter.setData(dataList = albums)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    companion object {
        const val ARG_ALBUM_ID = "album_id"
    }
}
