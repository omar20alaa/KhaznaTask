package com.app.khazna_task.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.khazna_task.R
import com.app.khazna_task.adapter.PostAdapter.PostViewHolder
import com.app.khazna_task.global.AppConstants
import com.app.khazna_task.model.Posts
import com.app.khazna_task.view.PostDetails
import com.google.gson.Gson

class PostAdapter(

        private val context: Context,
        private var postsList: List<Posts>) : RecyclerView.Adapter<PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val posts = postsList[position]
        holder.post_id!!.text = context.getString(R.string.post_id) + " :   #" + posts.id
        holder.post_title!!.text = context.getString(R.string.post_title) + " :   " + posts.title
        holder.itemView.setOnClickListener {
            Log.i(AppConstants.TAG, "go to product details")
            val intent = Intent(context, PostDetails::class.java)
            intent.putExtra("Product", Gson().toJson(posts))
            context.startActivity(intent)
        }
    }

    fun getAllPosts(postsList: List<Posts>) {
        this.postsList = postsList
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var post_id: TextView
        var post_title: TextView

        init {
            post_id = itemView!!.findViewById(R.id.post_id)
            post_title = itemView!!.findViewById(R.id.post_title)
        }
    }

}