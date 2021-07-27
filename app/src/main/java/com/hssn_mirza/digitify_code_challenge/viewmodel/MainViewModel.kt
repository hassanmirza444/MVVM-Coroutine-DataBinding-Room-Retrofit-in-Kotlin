package com.hssn_mirza.digitify_code_challenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hssn_mirza.digitify_code_challenge.data.repositories.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor() : ViewModel() {


    val mainRepository = MainRepository()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Error", "Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMovies() {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            mainRepository.getAllMovies()
        }
    }

    fun getSearchedMovies(search: String) {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            mainRepository.getSearchedMovies(search)
        }
    }


    override fun onCleared() {
        super.onCleared()

    }

}