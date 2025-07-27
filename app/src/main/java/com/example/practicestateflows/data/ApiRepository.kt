package com.example.practicestateflows.data

import com.example.practicestateflows.model.Post
import com.example.practicestateflows.model.Test
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface ApiRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getTests(): List<Test>
}

class NetworkApiRepository @Inject constructor(
    val apiService: ApiService
): ApiRepository {
    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }

    override suspend fun getTests(): List<Test> {
        return apiService.getTests()
    }
}

class FakeNetworkApiRepository @Inject constructor() : ApiRepository {
    override suspend fun getPosts(): List<Post> {
        throw Exception("Simulated Error")
    }

    override suspend fun getTests(): List<Test> {
        return listOf(Test("Test1"), Test("Test2"))
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        implements: FakeNetworkApiRepository
    ): ApiRepository
}