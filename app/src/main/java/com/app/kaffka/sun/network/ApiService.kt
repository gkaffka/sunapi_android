package com.app.kaffka.sun.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT

interface ApiService {

    @GET("day")
    fun getDay(): Call<DayResponse>

    @FormUrlEncoded
    @PUT("day")
    fun updateDay(@Field("sunrise") sunrise: String, @Field("sunset") sunset: String): Call<DayResponse>
}