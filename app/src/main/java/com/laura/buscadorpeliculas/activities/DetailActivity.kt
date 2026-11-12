package com.laura.buscadorpeliculas.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.laura.buscadorpeliculas.data.MovieApi
import com.laura.buscadorpeliculas.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movieId = intent.getStringExtra("MOVIE_ID")

        if (!movieId.isNullOrEmpty()) {
            cargarDetallePelicula(movieId)
        }
    }

    private fun cargarDetallePelicula(id: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Petición a OMDb para obtener los detalles completos por ID
                val movie = MovieApi.getInstance().getMovieById(id)

                withContext(Dispatchers.Main) {
                    binding.tvTitle.text = movie.title
                    binding.tvYear.text = "Año: ${movie.year ?: "N/A"}"
                    binding.tvRuntime.text = "Duración: ${movie.runtime ?: "N/A"}"
                    binding.tvGenre.text = "Género: ${movie.genre ?: "N/A"}"
                    binding.tvCountry.text = "País: ${movie.country ?: "N/A"}"
                    binding.tvPlot.text = movie.synopsis ?: "Sin sinopsis disponible."


                    if (!movie.poster.isNullOrEmpty() && movie.poster != "N/A") {
                        Picasso.get()
                            .load(movie.poster)
                            .placeholder(android.R.drawable.ic_menu_gallery)
                            .error(android.R.drawable.ic_menu_close_clear_cancel)
                            .into(binding.Poster)
                    } else {
                        binding.Poster.setImageResource(android.R.drawable.ic_menu_gallery)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}