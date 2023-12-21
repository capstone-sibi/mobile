package com.example.capstone.helper

import android.app.Activity
import android.content.Intent
import com.example.capstone.ui.main.MainActivity
import com.example.capstone.R
import com.example.capstone.ui.Dictionary.DictionaryActivity
import com.example.capstone.ui.quiz.QuizActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

object BottomNavigationHelper {

    fun setupBottomNavigation(activity: Activity, bottomNavView: BottomNavigationView) {
        bottomNavView.setOnItemSelectedListener{ item ->
            when (item.itemId){
                R.id.navigation_home -> {
                    if (activity !is MainActivity){
                        activity.startActivity(Intent(activity, MainActivity::class.java))
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_quiz -> {
                    if (activity !is QuizActivity){
                       activity.startActivity(Intent(activity, QuizActivity::class.java))
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dictionary->{
                    if (activity !is DictionaryActivity){
                        activity.startActivity(Intent(activity, DictionaryActivity::class.java))
                    }
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false
            }
        }
    }
}