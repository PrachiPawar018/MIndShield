package com.example.mindpulse.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mindpulse.R
import com.example.mindpulse.databinding.FragmentExploreBinding
import com.example.mindpulse.domain.model.Meditation
import com.example.mindpulse.ui.adapter.MeditationAdapter

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.rvExplore.layoutManager = LinearLayoutManager(requireContext())
        
        val list = listOf(
            Meditation(id = "1", title = "Anxiety Relief", category = "Meditation", duration = "12 mins", description = ""),
            Meditation(id = "2", title = "Rainy Night", category = "Sleep", duration = "45 mins", description = ""),
            Meditation(id = "3", title = "Classical Study", category = "Music", duration = "60 mins", description = ""),
            Meditation(id = "4", title = "Nature Walk", category = "Ambient", duration = "20 mins", description = ""),
            Meditation(id = "5", title = "Body Scan", category = "Meditation", duration = "15 mins", description = "")
        )
        
        val adapter = MeditationAdapter(list) { meditation ->
            val args = Bundle().apply {
                putString("title", meditation.title)
                putString("category", meditation.category)
                putString("duration", meditation.duration)
            }
            findNavController().navigate(R.id.action_explore_to_details, args)
        }
        binding.rvExplore.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}