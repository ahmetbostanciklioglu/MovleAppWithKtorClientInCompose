package com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.network.Resource
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.common.FullScreenProgressBar
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.common.toast
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.movies.MovieViewModel
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.movies.MovieList
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.theme.MovleAppWithKtorClientInComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val movies = viewModel.movies.collectAsState()
            MovleAppWithKtorClientInComposeTheme {
                movies.value?.let { movies ->
                    when (movies) {
                        is Resource.Failure -> {
                            context.toast(movies.exception.message!!)
                        }
                        Resource.Loading -> {
                            FullScreenProgressBar()
                        }
                        is Resource.Success -> {
                            MovieList(movies.result)
                        }
                    }
                }
            }
        }
    }
}

