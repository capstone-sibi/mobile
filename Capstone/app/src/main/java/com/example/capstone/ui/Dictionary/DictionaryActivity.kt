package com.example.capstone.ui.Dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.DictionaryAdapter
import com.example.capstone.databinding.ActivityDictionaryBinding


class DictionaryActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ABAOUT = "extra_abaout"
    }

    private lateinit var binding: ActivityDictionaryBinding
    private val dictionaryViewModel by viewModels<DictionaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@DictionaryActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
        dictionaryViewModel.users.observe(this@DictionaryActivity){
            val adapter = DictionaryAdapter()
            adapter.submitList(it)
            binding.recyclerView.adapter = adapter
        }

    }


}