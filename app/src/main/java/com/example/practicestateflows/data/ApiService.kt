package com.example.practicestateflows.data

import com.example.practicestateflows.model.Post
import com.example.practicestateflows.model.Test
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET
import javax.inject.Singleton

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post> = listOf(Post("Post1"), Post("Post2"))

    @GET("tests")
    suspend fun getTests(): List<Test> = listOf(Test("Test1"), Test("Test2"))
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun provideApiService(): ApiService
}