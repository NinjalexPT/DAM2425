package com.example.newsapp.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.module.Article
import com.example.newsapp.repositories.ArticleRepository
import com.example.newsapp.repositories.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

data class ArticlesState (
    val articles: List<Article> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    val articleRepository: ArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesState())
    val uiState : StateFlow<ArticlesState> = _uiState.asStateFlow()

    fun fetchArticles() {
        articleRepository
            .fetchArticles("top-headlines?country=us")
            .onEach { result ->
                when(result) {
                    is ResultWrapper.Success ->{
                        _uiState.value = ArticlesState(
                            articles = result.data?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is ResultWrapper.Error ->{
                        _uiState.value = ArticlesState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is ResultWrapper.Loading ->{
                        _uiState.value = ArticlesState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

}