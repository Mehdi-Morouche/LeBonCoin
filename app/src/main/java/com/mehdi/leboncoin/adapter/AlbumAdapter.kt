package com.mehdi.leboncoin.adapter

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mehdi.leboncoin.AlbumDetailActivity
import com.mehdi.leboncoin.BR
import com.mehdi.leboncoin.R
import com.mehdi.leboncoin.databinding.ItemHolderBinding
import com.mehdi.leboncoin.entities.AlbumEntity
import kotlinx.android.synthetic.main.item_holder.view.*

/**
 * Created by mehdi on 2020-06-16.
 */

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var albums = listOf<AlbumEntity>()

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

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var binding: ViewDataBinding? = DataBindingUtil.inflate<ItemHolderBinding>(
            inflater,
            R.layout.album_holder,
            parent,
            false
        )

        context = parent.context

        return ViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])

        val item = albums[position]
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataObject: AlbumEntity) {
            with(binding) {
                setVariable(BR.album, dataObject)
                executePendingBindings()
            }
        }
    }
}