package com.mehdi.leboncoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mehdi.leboncoin.databinding.ActivityAlbumItemBinding
import com.mehdi.leboncoin.entities.AlbumEntity
import com.mehdi.leboncoin.viewmodel.AlbumItemActivityViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Created by mehdi on 2020-06-16.
 */

class AlbumItemActivity : AppCompatActivity() {

    private lateinit var mModel: AlbumItemActivityViewModel

    lateinit var dataBinding: ActivityAlbumItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mModel = ViewModelProviders.of(this).get(AlbumItemActivityViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView<ActivityAlbumItemBinding>(this, R.layout.activity_album_item).apply {
            setLifecycleOwner(this@AlbumItemActivity)
            lifecycle.addObserver(mModel)

            val json = Json(JsonConfiguration.Stable)
            val item = json.parse(AlbumEntity.serializer(), intent?.getStringExtra(ARG_ALBUM)!!.toString())
            setVariable(BR.album, item)
        }

    }

    companion object {
        const val ARG_ALBUM = "album"
    }
}
