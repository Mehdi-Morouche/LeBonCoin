package com.mehdi.leboncoin

import android.os.Bundle
import android.view.MenuItem
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

        val item = intent?.getStringExtra(ARG_ALBUM)!!.toString()
        val json = Json(JsonConfiguration.Stable)
        val album = json.parse(AlbumEntity.serializer(), item)
        val albumId = album.albumId

        // Show the title of the album
        supportActionBar?.setTitle("Album $albumId")

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mModel = ViewModelProviders.of(this).get(AlbumItemActivityViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView<ActivityAlbumItemBinding>(this, R.layout.activity_album_item).apply {
            setLifecycleOwner(this@AlbumItemActivity)
            lifecycle.addObserver(mModel)

            setVariable(BR.album, album)
        }

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
        const val ARG_ALBUM = "album"
    }
}
