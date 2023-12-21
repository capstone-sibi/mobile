package com.example.capstone.ui.Dictionary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.DictionaryAdapter
import com.example.capstone.databinding.ActivityDictionaryBinding

class DictionaryActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ABAOUT = "extra_abaout"
    }

    private lateinit var binding: ActivityDictionaryBinding
    private val dictionaryViewModel by viewModels<DictionaryViewModel>()
    private lateinit var adapter: DictionaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
