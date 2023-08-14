package com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.repository

import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.models.Movie
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.network.Resource

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<List<Movie>>
}