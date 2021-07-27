package com.hssn_mirza.digitify_code_challenge.data.repositories

import com.hssn_mirza.digitify_code_challenge.data.networking.RetrofitService
import com.hssn_mirza.digitify_code_challenge.model.MovieModel


class MainRepository constructor() {


    suspend fun getAllMovies(): List<MovieModel>? {
        val response = RetrofitService.getInstance().getAllMovies()
        if (response.isSuccessful) {
            OfflineMoviesRepository.getInstance()!!.insertAll(response.body()!!.results);
            return response.body()!!.results
        } else {
            onError("Error : ${response.message()} ")
        }
        return null;
    }

    suspend fun getSearchedMovies(text:String): List<MovieModel>? {
        val response = RetrofitService.getInstance().getSearchedMovies()
        if (response.isSuccessful) {
            return response.body()!!.results
        } else {
            onError("Error : ${response.message()} ")
        }
        return null;
    }

    private fun onError(s: String) {

    }


}

