package com.example.myapplication.api

import com.example.myapplication.util.User
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface oAuthApi {
    @POST("token")
    suspend fun getInfoUser(@Header("Authorization") header:String,  @Body body: RequestBody): Response<User>
}