package com.app.khazna_task.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.app.khazna_task.dao.PostDao
import com.app.khazna_task.database.PostsDatabase
import com.app.khazna_task.database.PostsDatabase.Companion.getInstance
import com.app.khazna_task.model.Posts

class PostsRespository(application: Application?) {

    private val database: PostsDatabase?
    val allPosts: LiveData<List<Posts>>
    fun insert(postsList: List<Posts>) {
        InsertAsynTask(database).execute(postsList)
    }

    internal class InsertAsynTask(postsDatabase: PostsDatabase?) : AsyncTask<List<Posts>, Void, Void>() {
        private val postDao: PostDao
        protected override fun doInBackground(vararg lists: List<Posts>): Void? {
            postDao.insert(lists[0])
            return null
        }

        init {
            postDao = postsDatabase!!.postsDao()
        }
    }

    init {
        database = getInstance(application)
        allPosts = database!!.postsDao().allPosts
    }
}