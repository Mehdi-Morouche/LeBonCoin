package com.mehdi.leboncoin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mehdi.leboncoin.BR
import com.mehdi.leboncoin.R
import com.mehdi.leboncoin.databinding.ItemHolderBinding
import com.mehdi.leboncoin.entities.AlbumEntity

/**
 * Created by mehdi on 2020-06-15.
 */

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var albums = listOf<AlbumEntity>()

    private lateinit var context: Context

    /*private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as AlbumEntity
            /*val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                val json = Json(JsonConfiguration.Stable)
                putExtra(
                    ItemDetailFragment.ARG_ITEM_ID,
                    json.stringify(CarEntity.serializer(), item)
                )
            }*/
            //v.context.startActivity(intent)
        }
    }*/

    fun setData(dataList: List<AlbumEntity>) {
        albums = emptyList()
        albums = dataList
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
        return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])

        val item = albums[position]
        with(holder.itemView) {
            tag = item
            //setOnClickListener(onClickListener)
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