package com.app.khazna_task.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.khazna_task.R
import com.app.khazna_task.global.GlobalFunctions.showLog
import com.app.khazna_task.model.Posts
import com.app.khazna_task.viewModel.PostDetailsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_post_details.*

@AndroidEntryPoint
class PostDetails : AppCompatActivity() {

    //todo vars
    var posts: Posts? = null
    private val postDetailsViewModel: PostDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        data
        initToolbar()
    }

    // todo init view model
    @SuppressLint("SetTextI18n")
    private fun initViewModel() {
        user_id!!.text = postDetailsViewModel!!.getText(getString(R.string.userId) + " :   #" + posts!!.userId)
        postTitle!!.text = getString(R.string.post_title) + " :   " + posts!!.title
        body!!.text = getString(R.string.post_body) + " :   " + posts!!.body
    }

    private fun initToolbar() {
        toolbar!!.setNavigationOnClickListener { onBackPressed() }
        toolbar!!.title = "Post" + " #" + posts!!.id
    }

    val data: Unit
        get() {
            val intent = intent
            if (null != intent) {
                val gson = Gson()
                posts = gson.fromJson(intent.getStringExtra("Product"), Posts::class.java)
            }
            initViewModel()
            showLog("product details getTitle --> " + posts!!.title)
        }
}