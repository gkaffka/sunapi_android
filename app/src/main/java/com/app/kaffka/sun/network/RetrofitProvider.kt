package com.app.kaffka.sun.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val retrofit = Retrofit.Builder()
        .baseUrl("https://sunapi.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

val service = retrofit.create<ApiService>(ApiService::class.java)