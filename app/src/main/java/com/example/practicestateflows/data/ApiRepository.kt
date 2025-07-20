package com.example.practicestateflows.data

import com.example.practicestateflows.model.Post
import com.example.practicestateflows.model.Test

interface ApiRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getTests(): List<Test>
}

class NetworkApiRepository(
    val apiService: ApiService
): ApiRepository {
    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }

    override suspend fun getTests(): List<Test> {
        return apiService.getTests()
    }
}

class FakeNetworkApiRepository() : ApiRepository {
    override suspend fun getPosts(): List<Post> {
        throw Exception("Simulated Error")
    }

    override suspend fun getTests(): List<Test> {
        return listOf(Test("Test1"), Test("Test2"))
    }
}