package com.mehdi.leboncoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mehdi.leboncoin.adapter.Adapter
import com.mehdi.leboncoin.databinding.ActivityMainBinding
import com.mehdi.leboncoin.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mModel: MainActivityViewModel

    lateinit var dataBinding: ActivityMainBinding

    var adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            setLifecycleOwner(this@MainActivity)
            lifecycle.addObserver(mModel)
        }

        dataBinding.recycler?.adapter = adapter

        mModel.albums.observe(this, Observer { albums ->
            adapter.setData(dataList = albums)
        })
    }
}