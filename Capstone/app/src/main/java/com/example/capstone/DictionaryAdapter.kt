package com.example.capstone

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.Api.DictionaryResponse
import com.example.capstone.databinding.DetailVidioBinding
import com.example.capstone.ui.Dictionary.DictionaryActivity
import java.util.Dictionary

class DictionaryAdapter : ListAdapter <DictionaryResponse, DictionaryAdapter.ListViewHolder> (DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = DetailVidioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(getItem(position))


    class ListViewHolder(private val binding: DetailVidioBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dictionary: DictionaryResponse) {
//            Glide.with(itemView.context)
//                .load("${dictionary.path}")
//                .into(binding.imgItemPhoto)
            binding.tvItemName.text = dictionary.meaning
            itemView.setOnClickListener{
                val intent = Intent(itemView.context,DictionaryActivity::class.java)
                intent.putExtra(DictionaryActivity.EXTRA_ABAOUT,dictionary.path)
                itemView.context.startActivity(intent)
            }
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DictionaryResponse>() {
            override fun areItemsTheSame(
                oldItem: DictionaryResponse,
                newItem: DictionaryResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DictionaryResponse,
                newItem: DictionaryResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}