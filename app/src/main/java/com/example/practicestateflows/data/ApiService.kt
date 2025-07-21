package com.example.practicestateflows.data

import com.example.practicestateflows.model.Post
import com.example.practicestateflows.model.Test
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post> = listOf(Post("Post1"), Post("Post2"))

    @GET("tests")
    suspend fun getTests(): List<Test> = listOf(Test("Test1"), Test("Test2"))
}