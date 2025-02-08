package com.nathan.pokedex.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    fun getRetrofit(context: Context): Retrofit {
        // Configurando Cache
        val cacheSize = 10 * 1024 * 1024L //10mb
        val cache = Cache(context.cacheDir, cacheSize)

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
}