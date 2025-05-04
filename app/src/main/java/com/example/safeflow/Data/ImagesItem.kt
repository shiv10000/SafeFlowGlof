package com.example.safeflow.Data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsPhoto(
    @Json(name = "id") val id: String,
    @Json(name = "img_src") val imgSrc: String,
    // Add other fields as needed
)

// 2. Create a response wrapper class if the API returns an array within an object
@JsonClass(generateAdapter = true)
data class MarsPhotosResponse(
    @Json(name = "photos") val photos: List<MarsPhoto>)

