package ru.practicum.android.diploma.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilteringSettingsBinding


class FilteringSettingsFragment : Fragment() {

    private var _binding : FragmentFilteringSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilteringSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.workPlaceText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                findNavController().navigate(R.id.action_filteringSettingsFragment_to_selectWorkplaceFragment)
            }
        }
        binding.industryText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                findNavController().navigate(R.id.action_filteringSettingsFragment_to_selectIndustryFragment)
            }
        }

        binding.settingsFiltrationToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}