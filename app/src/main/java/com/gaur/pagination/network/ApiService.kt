package com.gaur.pagination.network

import com.gaur.pagination.model.FakeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/passenger")
    suspend fun getFakeResponse(
        @Query("page") page:Int,
        @Query("size") size:Int
    ):Response<FakeResponse>


}