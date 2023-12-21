package com.example.capstone.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.helper.BottomNavigationHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tvUri: TextView
    private lateinit var viewModel: MainViewModel

    private var videoPickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewModel.translate("http://35.203.44.52/", it.toString(), { result ->
                tvUri.text = result
            }, { error ->
                tvUri.text = error
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        tvUri = findViewById(R.id.tv_uri)
        val uploadButton: Button = findViewById(R.id.button_upload_video)
        uploadButton.setOnClickListener {
            openVideoUploader()
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navigation_home
    }

    private fun openVideoUploader() {
        videoPickerLauncher.launch("video/*")
    }
}