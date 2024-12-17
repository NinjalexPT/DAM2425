package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.module.Article
import com.example.newsapp.module.ArticleDao

@Database(entities = [Article::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase?{
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "db_daily_news"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }



}