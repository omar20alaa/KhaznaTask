package com.app.khazna_task.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import app.khazna_task.R
import com.app.khazna_task.adapter.ItemAdapter
import com.app.khazna_task.adapter.PostAdapter
import com.app.khazna_task.global.AppConstants
import com.app.khazna_task.global.GlobalFunctions.isConnected
import com.app.khazna_task.global.GlobalFunctions.showFailToast
import com.app.khazna_task.global.GlobalFunctions.showSuccessToast
import com.app.khazna_task.model.Posts
import app.khazna_task.paging.ItemViewModel
import com.app.khazna_task.viewModel.PostsViewModel
import kotlinx.android.synthetic.main.activity_post.*
import java.util.*

class PostActivity : AppCompatActivity() {

    // todo vars
    private var postsViewModel: PostsViewModel? = null
    private var postsLists: List<Posts>? = null
    private var postAdapter: PostAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        initRecyclerView()

        // todo check if connection is online load paginated list
        if (isConnected(this)) {
            initList()
            initSwipe()
        } else {
            showFailToast(this, "Offline")
            initViewModel()
        }
    }

    // todo init paging list
    fun initList() {
        val itemViewModel = ItemViewModel(application!!)
        val adapter = ItemAdapter(this@PostActivity)
        itemViewModel.itemPagedList.observe(this, Observer<PagedList<Posts>> { items ->
            adapter.submitList(items)
            // todo stop refresh when get data
            refresh_layout!!.setRefreshing(false)
        })
        recyclerview!!.adapter = adapter
        showSuccessToast(this, "Online")
    }

    private fun initSwipe() {
        refresh_layout!!.setOnRefreshListener { initList() }
    }

    // todo init view model method to get cashed list data when app is offline
    private fun initViewModel() {
        postsLists = ArrayList()
        postAdapter = PostAdapter(this, postsLists!!)
        postsViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        postsViewModel!!.allPosts.observe(this, Observer { postsList ->
            postAdapter!!.getAllPosts(postsList)
            recyclerview!!.adapter = postAdapter
            postsViewModel!!.fetchPosts()
            Log.d(AppConstants.TAG, "onChanged: $postsList")
            Log.d(AppConstants.TAG, "postsList: " + postsList.size)
        })
    }

    // todo initialize recyclerview
    private fun initRecyclerView() {
        recyclerview!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerview!!.layoutManager = layoutManager
        recyclerview!!.itemAnimator = DefaultItemAnimator()
    }
}