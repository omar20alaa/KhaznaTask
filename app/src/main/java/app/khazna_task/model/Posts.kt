package com.app.khazna_task.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post", indices = [Index(value = ["id"], unique = true)])

class Posts(@field:SerializedName("id")

            @field:PrimaryKey var id: Int,
            @field:ColumnInfo(name = "title")
            @field:SerializedName("title") var title: String,
            @field:ColumnInfo(name = "body")
            @field:SerializedName("body") var body: String,
            @field:ColumnInfo(name = "userId")
            @field:SerializedName("userId")
            var userId: Int) {

    var isHas_more = false

    override fun toString(): String {
        return "Posts{" +
                "userId=" + userId +
                ", id=" + id +
                ", body='" + body + '\'' +
                ", title='" + title + '\'' +
                '}'
    }

}