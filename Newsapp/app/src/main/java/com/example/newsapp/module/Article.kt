package com.example.newsapp.module

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.newsapp.encodeURL
import com.example.newsapp.parseDate
import com.example.newsapp.toServerDate
import org.json.JSONObject
import java.util.Date

@Entity
class Article (
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    @PrimaryKey
    var url: String,
    var publishedDate: Date? = null ){

    companion object{
        fun fromjson(json: JSONObject):Article{
            return Article(
                title = json.getString("title"),
                description = json.getString("description"),
                imageUrl = json.getString("urlToImage"),
                url = json.getString("url"),
                publishedDate = json.getString("publishedAt").parseDate()
            )
        }
    }

    fun toJsonString() : String {
        val jsonObject = JSONObject()
        jsonObject.put("title"       , title                        )
        jsonObject.put("description" , description                  )
        jsonObject.put("urlToImage"  , imageUrl?.encodeURL()      )
        jsonObject.put("url"         , url?.encodeURL()             )
        jsonObject.put("publishedAt" , publishedDate?.toServerDate()  )
        return jsonObject.toString()
    }
}

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Query("SELECT * FROM article WHERE url = :url")
    fun loadByUrl(url: String): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( article: Article)

    @Delete
    fun delete(article: Article)
}


