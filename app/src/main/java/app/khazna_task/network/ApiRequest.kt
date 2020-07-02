package com.app.khazna_task.network

import com.app.khazna_task.model.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    // todo fetch online posts from api
    @GET("posts")
    fun fetchOnlinePosts(
            @Query("_page") page: String?,  // page number
            @Query("_limit") limit: String? // limit size
    ): Call<List<Posts>>

    // todo fetch offline posts from api
    @GET("posts")
    fun fetchOfflinePosts(): Call<List<Posts>>
}