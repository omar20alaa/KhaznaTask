package com.app.khazna_task.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.khazna_task.R
import com.app.khazna_task.adapter.ItemAdapter.ItemViewHolder
import com.app.khazna_task.global.AppConstants
import com.app.khazna_task.model.Posts
import com.app.khazna_task.view.PostDetails
import com.google.gson.Gson

class ItemAdapter(private val mCtx: Context) : PagedListAdapter<Posts, ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.post_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val posts = getItem(position)
        holder.post_id!!.text = mCtx.getString(R.string.post_id) + " :   #" + posts!!.id
        holder.post_title!!.text = mCtx.getString(R.string.post_title) + " :   " + posts.title
        holder.itemView.setOnClickListener {
            Log.i(AppConstants.TAG, "go to product details")
            val intent = Intent(mCtx, PostDetails::class.java)
            intent.putExtra("Product", Gson().toJson(posts))
            mCtx.startActivity(intent)
        }
    }

    inner class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var post_id: TextView
        var post_title: TextView

        init {
            post_id = itemView!!.findViewById(R.id.post_id)
            post_title = itemView!!.findViewById(R.id.post_title)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Posts> = object : DiffUtil.ItemCallback<Posts>() {
            override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
                return oldItem == newItem
            }
        }
    }

}