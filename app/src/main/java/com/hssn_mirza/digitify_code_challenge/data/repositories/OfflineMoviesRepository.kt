package com.hssn_mirza.digitify_code_challenge.data.repositories

import androidx.lifecycle.LiveData
import com.hssn_mirza.digitify_code_challenge.MainApp
import com.hssn_mirza.digitify_code_challenge.model.MovieModel
import com.hssn_mirza.digitify_code_challenge.room.MoviesDao
import com.hssn_mirza.digitify_code_challenge.room.OfflineMoviesAppDatabase

class OfflineMoviesRepository{

    companion object {
        private var instance: OfflineMoviesRepository? = null
        private var db: OfflineMoviesAppDatabase? =  OfflineMoviesAppDatabase.getInstance(MainApp.getContext())
        private var moviesDao: MoviesDao? = db!!.moviesDao()


      /*  private fun OfflineMoviesRepository() {
            db = OfflineMoviesAppDatabase.getInstance(application)
            moviesDao = db!!.moviesDao()
        }*/

        fun getInstance(): OfflineMoviesRepository? {
            if (instance == null)
                instance = OfflineMoviesRepository()
            return instance
        }


    }

    suspend fun insertAll(list: List<MovieModel>?) {
        moviesDao!!.insertList(list);
    }

    suspend fun getAllMovies(): LiveData<List<MovieModel>> {
        return moviesDao!!.allMovieModels
    }


}