package com.example.userloginjwt

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("https://10.0.2.2/JWT_Auth/api/login.php/")
    suspend fun login(@Body reqBody: RequestBody): Response<ResponseBody>
}