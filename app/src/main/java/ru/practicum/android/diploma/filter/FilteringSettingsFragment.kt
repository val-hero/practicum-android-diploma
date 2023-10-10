package ru.practicum.android.diploma.filter

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilteringSettingsBinding
import ru.practicum.android.diploma.search.ui.viewmodel.SearchViewModel


class FilteringSettingsFragment : Fragment() {

    private var _binding : FragmentFilteringSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FilteringSettingsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilteringSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilterSettings().observe(viewLifecycleOwner) {
            Log.i("SETTINGSFILTER", "В настройках фильтра ${it}")
            if(it != null) {
                binding.workPlace.hint = it.country?.name
                binding.industry.hint = it.industry?.name
                binding.salary.text = it.salary as Editable?
            }
        }


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

    override fun onResume() {
        super.onResume()
        viewModel.updateFilterSettings()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}