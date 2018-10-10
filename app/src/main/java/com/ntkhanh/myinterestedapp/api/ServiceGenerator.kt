package com.ntkhanh.myinterestedapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private var mApiBaseUrl = "https://api.github.com/"
    private var builder = Retrofit.Builder() //create retrofit builder
            .baseUrl(mApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // add a converter: need Json to convert between data obj and Json

    // Create retrofit object
    private var retrofit = builder.build()

    private val httpClientBuilder = OkHttpClient.Builder()

    // For login we need an OK http interceptor
    private val httpInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun changeApiBaseUrl(newApiBaseUrl: String) {
        mApiBaseUrl = newApiBaseUrl
        builder = Retrofit.Builder()
                .baseUrl(mApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
    }
    
    fun <S> createService(serviceClass: Class<S>): S {

        if (!httpClientBuilder.interceptors().contains(httpInterceptor)) {
            httpClientBuilder.addInterceptor(httpInterceptor)
            // customize it with a custom OkHttp
            builder = builder.client(httpClientBuilder.build())
            // create retrofit instance from the builder
            retrofit = builder.build()
        }

        return retrofit.create(serviceClass)
    }

}