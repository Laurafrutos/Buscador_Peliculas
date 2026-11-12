package com.laura.buscadorpeliculas.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.laura.buscadorpeliculas.adapter.MovieAdapter
import com.laura.buscadorpeliculas.data.MovieApi
import com.laura.buscadorpeliculas.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = MovieAdapter { movie ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("MOVIE_ID", movie.id)
            }
            startActivity(intent)
        }
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter


        buscarPeliculas("Batman")


        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                buscarPeliculas(query)
            }
        }
    }

    private fun buscarPeliculas(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {

                val response = MovieApi.getInstance().searchMovies(query)

                withContext(Dispatchers.Main) {
                    if (response.response == "True" && !response.search.isNullOrEmpty()) {
                        adapter.submitList(response.search)
                    } else {
                        adapter.submitList(emptyList())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}