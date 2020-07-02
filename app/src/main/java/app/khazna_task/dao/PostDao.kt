package com.app.khazna_task.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.khazna_task.model.Posts

@Dao
interface PostDao {


    // todo insert posts in room database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postsList: List<Posts>)

    // todo make query to select from (post) : the table name ) to get all list posts
    @get:Query("SELECT * FROM post")
    val allPosts: LiveData<List<Posts>>
}