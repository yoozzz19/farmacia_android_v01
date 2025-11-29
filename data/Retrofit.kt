package com.example.farmaciaDrPerez.data

import com.example.farmaciaDrPerez.apis.PharmacyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.getValue

object PharmacyInstance {
    private const val URL_BASE = " https://sandee-tweediest-sasha.ngrok-free.dev"

    val myRetrofit by lazy {
        Retrofit.Builder().baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val API : PharmacyApi by lazy {
        myRetrofit.create(PharmacyApi::class.java)
    }
}