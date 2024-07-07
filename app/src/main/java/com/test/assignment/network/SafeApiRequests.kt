package com.test.assignment.network

import retrofit2.Response

abstract class SafeApiRequests {
    private var message: StringBuilder? = null
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        if (response.isSuccessful && response.code() == 200) {
            return response.body()
        } else {
            throw APIExceptions(message?.toString())
        }
    }
}