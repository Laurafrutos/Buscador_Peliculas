package com.laura.buscadorpeliculas.data

import com.google.gson.annotations.SerializedName


data class MovieSearchResponse(
    @SerializedName("Search") val search: List<Movie>? = null,
    @SerializedName("totalResults") val totalResults: String? = null,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String? = null
)


data class Movie(
    @SerializedName("Title") val title: String?,
    @SerializedName("Year") val year: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("imdbID") val id: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Runtime") val runtime: String?,
    @SerializedName("Country") val country: String?,
    @SerializedName("Plot") val synopsis: String?
)