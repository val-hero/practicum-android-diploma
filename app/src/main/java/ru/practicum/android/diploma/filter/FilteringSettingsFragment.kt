package ru.practicum.android.diploma.filter

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilteringSettingsBinding
import ru.practicum.android.diploma.filter.domain.models.FilterParameters


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
            render(it)
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

        binding.applyButton.setOnClickListener {
            viewModel.saveSalarySettings(binding.salary.text.toString(), binding.checkbox.isChecked)
            findNavController().popBackStack()
        }

        binding.clearButton.setOnClickListener {
            viewModel.clearFilterSettings()
            findNavController().popBackStack()
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

    private fun render(filterParameters: FilterParameters?) {
        if (filterParameters == null) {
            binding.btnGroup.visibility = View.GONE
            binding.checkbox.isChecked = false
        } else {
            binding.btnGroup.visibility = View.VISIBLE
            if(filterParameters.area != null) binding.workPlaceText.setText(filterParameters.area?.name)
            if(filterParameters.industry != null) binding.industryText.setText(filterParameters.industry?.name)
            if(filterParameters.salary != null) binding.salary.setText(filterParameters.salary?.toString())
            if(filterParameters.onlyWithSalary == true) binding.checkbox.isChecked = true else binding.checkbox.isChecked = false
        }
    }

}