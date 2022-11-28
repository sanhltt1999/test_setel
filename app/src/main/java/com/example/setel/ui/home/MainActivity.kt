package com.example.setel.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.setel.R
import com.example.setel.ui.dialog.LoadingProgress
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val progress: LoadingProgress by lazy {
        LoadingProgress()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getErrorProducts()

        viewModel.dataLoading.observe(this) {
            if (it) progress.showLoadingProgress(this) else progress.hideLoadingProgress()
        }
    }
}