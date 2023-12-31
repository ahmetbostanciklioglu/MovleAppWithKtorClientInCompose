package com.ahmet.bostanciklioglu.movleappwithktorclientincompose.data.network

import android.util.Log
import com.ahmet.bostanciklioglu.movleappwithktorclientincompose.ui.common.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class TmdbHttpClient @Inject constructor() {

    fun getHttpClient() = HttpClient(Android) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v(TAG_KTOR_LOGGER, message)
                }
            }
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
        install(ResponseObserver) {
            onResponse { response ->
                Log.d(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
            }
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            parameter("api_key", Constants.API_KEY)
        }
    }
    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }

}