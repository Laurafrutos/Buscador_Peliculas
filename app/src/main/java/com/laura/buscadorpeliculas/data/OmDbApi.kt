package com.laura.buscadorpeliculas.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieSearchResponse

    @GET("/")
    suspend fun getMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = API_KEY
    ): Movie

    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"
        private const val API_KEY = "3eff5212"

        fun getInstance(): MovieApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieApi::class.java)
        }
    }
}