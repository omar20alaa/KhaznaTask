package com.app.khazna_task.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.khazna_task.dao.PostDao
import com.app.khazna_task.model.Posts

@Database(entities = [Posts::class], version = 3)

abstract class PostsDatabase : RoomDatabase() {
    abstract fun postsDao(): PostDao

    internal class PopulateAsynTask(postsDatabase: PostsDatabase?) : AsyncTask<Void, Void, Void>() {
        var postDao: PostDao
        override fun doInBackground(vararg voids: Void): Void? {
            return null
        }

        init {
            postDao = postsDatabase!!.postsDao()
        }
    }

    companion object {
        private const val DATABASE_NAME = "PostsDatabase"
        private var INSTANCE: PostsDatabase? = null

        // todo sigleton to fetch data only if first time and create object in memory one time
        @JvmStatic
        fun getInstance(context: Context?): PostsDatabase? {
            if (INSTANCE == null) {
                synchronized(PostsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context!!, PostsDatabase::class.java,
                                DATABASE_NAME)
                                .addCallback(callback)
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        var callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsynTask(INSTANCE)
            }
        }
    }
}