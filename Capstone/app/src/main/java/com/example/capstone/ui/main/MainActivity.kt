package com.example.capstone.ui.main

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.helper.BottomNavigationHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import okio.source
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tvUri: TextView
    private lateinit var videoView: VideoView
    private lateinit var viewModel: MainViewModel

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private var videoPickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val requestBody = createRequestBody(it, contentResolver)
            val filePart = MultipartBody.Part.createFormData("file", "video.mp4", requestBody)
            viewModel.translate(filePart, { result ->
                tvUri.text = result
            }, { error ->
                showToast(error.toString())
            })
        }
    }

    fun createRequestBody(uri: Uri, contentResolver: ContentResolver): RequestBody{
        return object : RequestBody() {
            override fun contentType() = "multipart/form-data".toMediaType()

            override fun writeTo(sink: BufferedSink) {
                contentResolver.openInputStream(uri)?.use {
                    sink.writeAll(it.source())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        supportActionBar?.apply {
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)

            setLogo(R.drawable.logo_text_black)

            title = "TERURA"
        }

        tvUri = findViewById(R.id.tv_uri)
        binding.buttonUploadVideo.setOnClickListener {
            openVideoUploader()
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navigation_home
    }

    private fun openVideoUploader() {
        videoPickerLauncher.launch("video/*")
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}