package com.example.task2.api

import com.example.task2.api.model.ReposResponse
import retrofit2.http.*

interface WebServices {

    @GET("repos")
    suspend fun getAllRepos(): List<ReposResponse.ReposResponseItem>;


}