package com.hssn_mirza.digitify_code_challenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hssn_mirza.digitify_code_challenge.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}