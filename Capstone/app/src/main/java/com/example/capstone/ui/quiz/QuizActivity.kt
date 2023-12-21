package com.example.capstone.ui.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.R
import com.example.capstone.helper.BottomNavigationHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navigation_quiz
    }
}