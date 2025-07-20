package com.example.practicestateflows.ui.theme.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicestateflows.data.FakeNetworkApiRepository

@Composable
fun MainScreen (viewModel: MainScreenViewModel) {

    val uiStatePosts by viewModel.uiStatePost.collectAsState()
    val uiStateTest = viewModel.uiStateTest.collectAsState()

    Column(
        modifier = Modifier
    ) {
        Text("📬 Posts")

        when (uiStatePosts){
            is PostUiState.Loading -> CircularProgressIndicator()
            is PostUiState.Error -> Text((uiStatePosts as PostUiState.Error).message)
            is PostUiState.Success -> {
                val posts = (uiStatePosts as PostUiState.Success).posts
                LazyColumn {
                    items(posts) {
                        post -> Text(post.name)
                    }
                }
            }
        }

        Text("Tests")

        when (uiStateTest.value){
            is TestUiState.Loading -> CircularProgressIndicator()
            is TestUiState.Error -> Text("Error")
            is TestUiState.Success -> {
                val tests = (uiStateTest.value as TestUiState.Success).tests
                LazyColumn {
                    items(tests) {
                            test -> Text(test.name)
                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        viewModel = MainScreenViewModel(
            repository = FakeNetworkApiRepository()))
}
