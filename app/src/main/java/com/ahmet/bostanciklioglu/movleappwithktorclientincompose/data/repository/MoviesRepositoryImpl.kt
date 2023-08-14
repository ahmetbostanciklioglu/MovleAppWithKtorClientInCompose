package com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.repository

import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.models.Movie
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.models.PopularMovies
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.network.Resource
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.common.Constants.POPULAR_MOVIES
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject



class MoviesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieRepository {
    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return  try {
            Resource.Success(
                httpClient.get<PopularMovies>{
                    url(POPULAR_MOVIES)
                }.movies
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

}