package com.example.newsapp.module

import com.example.newsapp.parseDate
import org.json.JSONObject
import java.util.Date

class Article (
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var url: String? = null,
    var publishedDate: Date? = null){

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
}



