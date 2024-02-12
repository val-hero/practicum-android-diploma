package ru.practicum.android.diploma.team.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.developer1.alpha = 0f
        binding.developer2.alpha = 0f
        binding.developer3.alpha = 0f
        binding.developer4.alpha = 0f
        binding.developer1.animate().alpha(1f).translationYBy((-50).toFloat()).setStartDelay(300).duration=1500
        binding.developer2.animate().alpha(1f).translationYBy((-50).toFloat()).setStartDelay(300).duration=1500
        binding.developer3.animate().alpha(1f).translationYBy((-50).toFloat()).setStartDelay(300).duration=1500
        binding.developer4.animate().alpha(1f).translationYBy((-50).toFloat()).setStartDelay(300).duration=1500
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}