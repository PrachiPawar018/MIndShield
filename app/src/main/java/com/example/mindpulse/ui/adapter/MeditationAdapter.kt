package com.example.mindpulse.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindpulse.domain.model.Meditation
import com.example.mindpulse.databinding.ItemCardBinding

class MeditationAdapter(
    private val meditations: List<Meditation>,
    private val onItemClick: (Meditation) -> Unit
) : RecyclerView.Adapter<MeditationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meditation = meditations[position]
        holder.bind(meditation)
    }

    override fun getItemCount(): Int = meditations.size

    inner class ViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meditation: Meditation) {
            binding.tvTitle.text = meditation.title
            binding.tvCategory.text = "${meditation.category} • ${meditation.duration}"
            
            // In a real app, use Glide to load meditation.imageUrl into binding.ivThumbnail
            
            binding.root.setOnClickListener {
                onItemClick(meditation)
            }
        }
    }
}