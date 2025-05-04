package com.example.safeflow.network

import com.example.safeflow.Data.MarsPhoto
import com.example.safeflow.Data.MarsPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface MarsApiService {
    // Option 1: If the API returns a direct list of photos
    @GET("photos")
    suspend fun getPhotos(@Query("sol") sol: String): List<MarsPhoto>

    // Option 2: If the API returns photos wrapped in an object
    // @GET("photos")
    // suspend fun getPhotos(@Query("sol") sol: String): MarsPhotosResponse
}



object NetworkModule {
    private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val marsApiService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}