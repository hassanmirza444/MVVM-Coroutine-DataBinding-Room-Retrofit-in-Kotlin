package com.hssn_mirza.digitify_code_challenge.data.networking

import com.hssn_mirza.digitify_code_challenge.model.AllMoviesResponseModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("movie")
    suspend fun getAllMovies(): Response<AllMoviesResponseModel>

    @GET("search/movie")
    suspend fun getSearchedMovies(): Response<AllMoviesResponseModel>



    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/discover/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClientBuilder())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }


        fun getOkHttpClientBuilder(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url =
                    originalHttpUrl.newBuilder().addQueryParameter("api_key", "83d01f18538cb7a275147492f84c3698")
                        .build()
                request.url(url)
                val response = chain.proceed(request.build())
                return@addInterceptor response
            }
            return builder.build()
        }

    }
}