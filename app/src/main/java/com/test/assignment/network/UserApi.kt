package com.test.assignment.network

import com.test.assignment.network.entity.TestResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    class BuildApi {
        fun apiService(url: String): UserApi? {
          return RetrofitNetworking.getClient(url)?.create(UserApi::class.java)
        }
    }


    @FormUrlEncoded
    @POST(API_TEST)
    suspend fun testApi(
        @Field("kid_id") username: Int,
    ): Response<TestResponse>
}