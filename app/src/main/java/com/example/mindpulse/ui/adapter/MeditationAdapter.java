package com.example.mindpulse.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mindpulse.data.model.Meditation;
import com.example.mindpulse.databinding.ItemCardBinding;

import java.util.List;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.ViewHolder> {

    private final List<Meditation> meditations;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Meditation meditation);
    }

    public MeditationAdapter(List<Meditation> meditations, OnItemClickListener listener) {
        this.meditations = meditations;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meditation meditation = meditations.get(position);
        holder.binding.tvTitle.setText(meditation.getTitle());
        holder.binding.tvCategory.setText(meditation.getCategory() + " • " + meditation.getDuration());
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(meditation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meditations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemCardBinding binding;

        public ViewHolder(ItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}