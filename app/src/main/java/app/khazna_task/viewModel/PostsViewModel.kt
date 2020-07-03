package com.app.khazna_task.viewModel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.khazna_task.global.GlobalFunctions.showLog
import com.app.khazna_task.model.Posts
import com.app.khazna_task.network.RetrofitClient.Companion.instance
import com.app.khazna_task.repository.PostsRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application) {

    private val postsRespository: PostsRespository
    val allPosts: LiveData<List<Posts>>

    fun fetchPosts() {
        instance!!.fetchOfflinePosts()
                .enqueue(object : Callback<List<Posts>> {
                    override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                        showLog("response in ViewModel --> " + response.body()!!.size)
                        allPosts.value
                        postsRespository.insert(response.body()!!)
                    }

                    override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                        showLog("error in ViewModel --> " + t.localizedMessage)
                    }
                })
    } // fetchPosts

    init {
        postsRespository = PostsRespository(application)
        allPosts = postsRespository.allPosts
    }
}