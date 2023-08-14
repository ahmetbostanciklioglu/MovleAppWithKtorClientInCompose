package com.ahmet.bostanciklioglu.movleappwithktorclientincompose.di

import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.network.TmdbHttpClient
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.repository.MovieRepository
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun getHttpClient(httpClient: TmdbHttpClient): HttpClient = httpClient.getHttpClient()

    @Provides
    fun getMoviesRepository(impl: MoviesRepositoryImpl): MovieRepository = impl
}