package com.example.practicestateflows.ui.theme.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicestateflows.data.ApiRepository
import com.example.practicestateflows.model.Post
import com.example.practicestateflows.model.Test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class PostUiState {
    object Loading: PostUiState()
    data class Error(val message: String): PostUiState()
    data class Success(val posts: List<Post>): PostUiState()
}

sealed class TestUiState {
    object Loading: TestUiState()
    data class Success(val tests: List<Test>): TestUiState()
    data class Error(val message: String): TestUiState()
}

class MainScreenViewModel(
    val repository: ApiRepository
): ViewModel() {

    private val _uiStatePost: MutableStateFlow<PostUiState> = MutableStateFlow(PostUiState.Loading)
    val uiStatePost: StateFlow<PostUiState> = _uiStatePost.asStateFlow()

    private val _uiStateTest: MutableStateFlow<TestUiState> = MutableStateFlow(TestUiState.Loading)
    val uiStateTest: StateFlow<TestUiState> = _uiStateTest.asStateFlow()

    init {
        getPosts()
        getTests()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _uiStatePost.update { PostUiState.Success(repository.getPosts()) }
            } catch (e: Exception) {
                _uiStatePost.update { PostUiState.Error("Error ${e.message}") }
            }
        }
    }

    private fun getTests() {
        viewModelScope.launch {
            try {
                _uiStateTest.update { TestUiState.Success(repository.getTests()) }
            } catch (e: Exception) {
                _uiStateTest.update { TestUiState.Error(e.message ?: "Unknown error") }
            }
        }
    }
}