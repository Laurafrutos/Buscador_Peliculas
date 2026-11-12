package com.laura.buscadorpeliculas.data

import com.google.gson.annotations.SerializedName

// Envoltorio seguro para la búsqueda
data class MovieSearchResponse(
    @SerializedName("Search") val search: List<Movie>? = null,
    @SerializedName("totalResults") val totalResults: String? = null,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String? = null // Captura el mensaje si falla
)

// Modelo de Película totalmente seguro contra nulos
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