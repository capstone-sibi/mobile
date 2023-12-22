package com.example.capstone.ui.Dictionary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.DictionaryAdapter
import com.example.capstone.R
import com.example.capstone.databinding.ActivityDictionaryBinding
import com.example.capstone.helper.BottomNavigationHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class DictionaryActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ABOUT = "extra_about"
    }

    private lateinit var binding: ActivityDictionaryBinding
    private val dictionaryViewModel by viewModels<DictionaryViewModel>()
    private lateinit var adapter: DictionaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        supportActionBar?.apply {
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)

            setLogo(R.drawable.logo_text_black)

            title = "TERURA"
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        BottomNavigationHelper.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navigation_dictionary

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchView.hide()
                Toast.makeText(this@DictionaryActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }

    private fun observeViewModel() {
        dictionaryViewModel.users.observe(this@DictionaryActivity) { userList ->
            adapter = DictionaryAdapter()
            adapter.submitList(userList)
            binding.recyclerView.adapter = adapter
        }
    }
}
