package com.example.newsapp

import android.content.Context
import com.example.newsapp.repositories.ArticlesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext app: Context) : Context{
        return app
    }

    @Provides
    @Singleton
    fun provideArticleApi() : ArticlesAPI {
        return ArticlesAPI
    }
}