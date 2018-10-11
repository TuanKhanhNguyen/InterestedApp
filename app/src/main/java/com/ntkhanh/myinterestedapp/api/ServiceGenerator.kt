package com.ntkhanh.myinterestedapp.api

import android.text.TextUtils
import com.google.gson.GsonBuilder
import com.ntkhanh.myinterestedapp.util.GsonUTCDateAdapter
import com.ntkhanh.myinterestedapp.util.LiveDataCallAdapterFactory
import com.ntkhanh.myinterestedapp.util.SerializationExclusionStrategy
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ServiceGenerator {

    private var mApiBaseUrl: String? = null
    private var mRetrofitBuilder = Retrofit.Builder() //create mRetrofit mRetrofitBuilder
            .baseUrl(mApiBaseUrl)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder()
                            .addSerializationExclusionStrategy(SerializationExclusionStrategy())
                            .registerTypeAdapter(Date::class.java, GsonUTCDateAdapter())
                            .create()
            )) // add a converter: need Json to convert between data obj and Json


    // Create mRetrofit object
    private var mRetrofit: Retrofit? = null// = mRetrofitBuilder.build()

    // For login we need an OK http interceptor
    private val mLoggingHttpInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val mHttpClientBuilder = OkHttpClient.Builder()

    fun setApiBaseUrl(newApiBaseUrl: String) {
        if (!TextUtils.equals(mApiBaseUrl, newApiBaseUrl)) {
            mApiBaseUrl = newApiBaseUrl
            mRetrofitBuilder.baseUrl(mApiBaseUrl)
            mRetrofit = mRetrofitBuilder.build()
        }
    }

    fun setIntercepter(interceptor: Interceptor?) {
        mHttpClientBuilder.interceptors().clear()
        mHttpClientBuilder.addInterceptor(mLoggingHttpInterceptor)
        interceptor?.let {
            if (!mHttpClientBuilder.interceptors().contains(mLoggingHttpInterceptor)) {
                mHttpClientBuilder.addInterceptor(interceptor)
            }
        }
        mRetrofitBuilder.client(mHttpClientBuilder.build())
        mRetrofit = mRetrofitBuilder.build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return mRetrofit!!.create(serviceClass)
    }

}