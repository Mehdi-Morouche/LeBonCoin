package com.mehdi.leboncoin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mehdi.leboncoin.AlbumDetailActivity
import com.mehdi.leboncoin.R
import com.mehdi.leboncoin.databinding.ItemHolderBinding
import com.mehdi.leboncoin.entities.AlbumEntity
import kotlinx.android.synthetic.main.item_holder.view.*

/**
 * Created by mehdi on 2020-06-15.
 */

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var albums = listOf<AlbumEntity>()

    private var albumsSize = mutableListOf<String>()

    private var cpt = 0

    private lateinit var context: Context

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as String
            val intent = Intent(v.context, AlbumDetailActivity::class.java).apply {
                putExtra(AlbumDetailActivity.ARG_ALBUM_ID, item)
            }
            v.context.startActivity(intent)
        }
    }

    fun setData(dataList: List<AlbumEntity>) {
        albums = emptyList()
        albums = dataList
        albumsSize.clear()

        cpt = 0

        for (album in albums) {
            if (albumsSize.size == 0) {
                albumsSize.add(album.albumId.toString())
            }

            if (!album.albumId.toString().equals(albumsSize.get(cpt))) {
                albumsSize.add(album.albumId.toString())
                cpt++
            }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var binding: ViewDataBinding? = DataBindingUtil.inflate<ItemHolderBinding>(
            inflater,
            R.layout.item_holder,
            parent,
            false
        )

        context = parent.context

        return ViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return albumsSize.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumsSize[position])

        val item = albumsSize[position]
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataObject: String) {
            itemView.album.text = dataObject
        }
    }
}