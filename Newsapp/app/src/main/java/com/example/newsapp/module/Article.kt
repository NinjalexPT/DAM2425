package com.example.newsapp.module

import org.jetbrains.annotations.Async
import java.util.Date

class Article (
    var title: String,
    var description: String,
    var content: String,
    var url: String,
    var publishedDate: Date,
    var imageUrl: String,
)

