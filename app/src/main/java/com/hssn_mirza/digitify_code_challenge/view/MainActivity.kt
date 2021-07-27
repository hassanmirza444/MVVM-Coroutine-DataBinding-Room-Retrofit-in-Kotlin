package com.hssn_mirza.digitify_code_challenge.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.hssn_mirza.digitify_code_challenge.MovieAdapter
import com.hssn_mirza.digitify_code_challenge.data.repositories.OfflineMoviesRepository
import com.hssn_mirza.digitify_code_challenge.databinding.ActivityMainBinding
import com.hssn_mirza.digitify_code_challenge.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    var permissions = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter(this)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        checkPermissions()
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                OfflineMoviesRepository.getInstance()!!.getAllMovies().observe(
                    this@MainActivity, {
                        adapter.setMovies(it)
                    }
                )
            }
        }
        viewModel.getAllMovies()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


            }
        })


    }


    private fun checkPermissions(): Boolean {
        var result: Int
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(this, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 100)
            return false
        }
        return true
    }
}