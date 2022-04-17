package com.example.rickmorty.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

data class RAMInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
data class LocationResponse(
    val name: String?,
    val url: String?
)
data class CharacterResponse(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val image: String?,
    val location: LocationResponse,
    val episode: List<String>?
)

data class RAMResponseItems<T>(
    @SerializedName("info") val info: RAMInfo,
    @SerializedName("results") val results: List<T>
)

interface RAMService {
    @GET("character")
    suspend fun getCharactersList(@Query("page") page: Int): RAMResponseItems<CharacterResponse>
}