package com.test.assignment.ui

import com.test.assignment.network.SafeApiRequests
import com.test.assignment.network.UserApi
import com.test.assignment.network.entity.TestResponse

class MainRepository(private var userApi: UserApi) : SafeApiRequests() {

    /*Implement Api*/
    suspend fun testApi(
        kid_id: Int,
    ): TestResponse? {
        return apiRequest {
            userApi.testApi(kid_id)
        }
    }


}
