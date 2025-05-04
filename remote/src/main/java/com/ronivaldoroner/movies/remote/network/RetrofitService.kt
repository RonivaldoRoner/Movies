package com.ronivaldoroner.movies.remote.network

import com.ronivaldoroner.movies.remote.BuildConfig
import com.ronivaldoroner.movies.remote.constants.Route
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit


internal object RetrofitService {
    private val DEFAULT_TIMEOUT_SECONDS = 30L

    private val client = OkHttpClient.Builder().build()

    private val contentType = "application/json".toMediaType()

    val networkJson = Json { ignoreUnknownKeys = true }

    fun <T> createWebService(service: Class<T>): T =
        makeRetrofit(baseURL(), accessTokenInterceptor()).create(service)

    private fun makeRetrofit(url: String, vararg interceptors: Interceptor): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(makeHttpClient(interceptors))
            .addConverterFactory(networkJson.asConverterFactory(contentType))
            .build()

    private fun makeHttpClient(interceptors: Array<out Interceptor>): OkHttpClient =
        client.newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor())
            .apply { interceptors().addAll(interceptors) }
            .addInterceptor(loggingInterceptor())
            .build()

    private fun loggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun headersInterceptor(): Interceptor =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build()
            )
        }

    private fun accessTokenInterceptor(): Interceptor =
        Interceptor { chain ->
            val response = chain.proceed(
                buildAuthorizedRequest(
                    chain
                )
            )

            response
        }

    private fun buildAuthorizedRequest(chain: Interceptor.Chain): Request {
        val request = chain.request()

        return request.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TheMovieDbAPIKey}")
            .addHeader("api_key", BuildConfig.TheMovieDbAccessKey)
            .method(request.method, request.body)
            .build()
    }

    private fun baseURL(): String = Route.BASE_URL

}