package com.app.khazna_task.paging

import androidx.paging.PageKeyedDataSource
import com.app.khazna_task.global.GlobalFunctions.showLog
import com.app.khazna_task.model.Posts
import com.app.khazna_task.network.RetrofitClient.Companion.instance
import com.app.khazna_task.repository.PostsRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource(private val postsRespository: PostsRespository) : PageKeyedDataSource<Int, Posts>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Posts>) {
        instance!!
                .fetchRepositories(FIRST_PAGE.toString() + "", PAGE_SIZE.toString() + "")
                .enqueue(object : Callback<List<Posts>> {
                    override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                        if (response.body() != null) {
                            callback.onResult(response.body()!!, null, FIRST_PAGE + 1)
                            postsRespository.insert(response.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                        showLog("error loadInitial --> " + t.localizedMessage)
                    }
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Posts?>) {
        instance!!
                .fetchRepositories(params.key.toString() + "", PAGE_SIZE.toString() + "")
                .enqueue(object : Callback<List<Posts>> {
                    override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                        if (response.body() != null) {
                            val key = if (params.key > 1) params.key - 1 else null
                            callback.onResult(response.body()!!, key)
                            postsRespository.insert(response.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                        showLog("error loadBefore --> " + t.localizedMessage)
                    }
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Posts?>) {
        instance!!
                .fetchRepositories(params.key.toString() + "", PAGE_SIZE.toString() + "")
                .enqueue(object : Callback<List<Posts>> {
                    override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                        if (response.body() != null) {
                            val key = params.key + 1
                            callback.onResult(response.body()!!, key)
                            postsRespository.insert(response.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                        showLog("error loadAfter --> " + t.localizedMessage)
                    }
                })
    }

    companion object {
        const val PAGE_SIZE = 5
        private const val FIRST_PAGE = 1
    }

}