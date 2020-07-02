package com.app.khazna_task.network

import com.app.khazna_task.global.AppConstants
import com.app.khazna_task.model.Posts
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val apiRequest: ApiRequest
    var logging = HttpLoggingInterceptor()

    //============================================================================================
    fun fetchRepositories(page_index: String?, limit: String?): Call<List<Posts>> {
        return apiRequest.fetchOnlinePosts(
                page_index,
                limit
        )
    } // fetchRepositories

    //============================================================================================
    fun fetchOfflinePosts(): Call<List<Posts>> {
        return apiRequest.fetchOfflinePosts()
    } // fetchOfflinePosts

    companion object {
        //============================================================================================
        // get Instance
        @JvmStatic
        var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }
            private set

    }

    //============================================================================================
    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        apiRequest = retrofit.create(ApiRequest::class.java)
    } // Retrofit Client Method
}