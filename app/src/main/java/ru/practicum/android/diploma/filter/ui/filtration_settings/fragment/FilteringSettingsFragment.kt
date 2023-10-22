package ru.practicum.android.diploma.filter.ui.filtration_settings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilteringSettingsBinding
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.filter.ui.filtration_settings.viewmodel.FilteringSettingsViewModel


class FilteringSettingsFragment : Fragment() {

    private var _binding: FragmentFilteringSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FilteringSettingsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilteringSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filterSettings.observe(viewLifecycleOwner) {
            initEditTextListener(it)
            render(it)

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            restoreSettingsAndNavBack()
        }

        binding.workPlaceText.setOnClickListener {
            findNavController().navigate(R.id.action_filteringSettingsFragment_to_selectWorkplaceFragment)
        }
        binding.industryText.setOnClickListener {
            findNavController().navigate(R.id.action_filteringSettingsFragment_to_selectIndustryFragment)
        }

        binding.settingsFiltrationToolbar.setNavigationOnClickListener {
            restoreSettingsAndNavBack()
        }

        binding.applyButton.setOnClickListener {
            viewModel.saveSalary(binding.salary.text.toString())
            findNavController().navigateUp()
        }

        binding.clearButton.setOnClickListener {
            viewModel.clearFilterSettings()
            findNavController().navigateUp()
        }

        binding.salaryFlagCheckbox.setOnClickListener {
            if (binding.salaryFlagCheckbox.isChecked) {
                viewModel.saveSalaryFlag(true)
            } else {
                viewModel.saveSalaryFlag(null)
            }
            viewModel.getFilterSettings()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFilterSettings()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    private fun render(filterParameters: FilterParameters?) {
        if (filterParameters == null) {
            binding.btnGroup.visibility = View.GONE
            binding.salaryFlagCheckbox.isChecked = false
            clearFields()
        } else {
            binding.btnGroup.visibility = View.VISIBLE
            if(filterParameters.country == null) {
                filterParameters.area?.countryId?.let {
                    viewModel.setCountryById(it)
                    viewModel.getFilterSettings()
                }
            }
            renderAreaField(filterParameters.country?.name, filterParameters.area?.name)
            renderIndustryField(filterParameters.industry)
            if (filterParameters.salary != null) binding.salary.setText(filterParameters.salary?.toString())
            binding.salaryFlagCheckbox.isChecked = filterParameters.onlyWithSalary == true
        }
    }

    private fun renderIndustryField(industry: Industry?) {
        if (industry != null) {
            binding.industryText.setText(industry.name)
            binding.industry.setEndIconDrawable(R.drawable.ic_close)
            binding.industry.setEndIconOnClickListener {
                viewModel.clearIndustryField()
                viewModel.getFilterSettings()
                clearIndustryField()
            }
        } else {
            clearIndustryField()
        }
    }

    private fun renderAreaField(country: String?, area: String?) {
        if (country != null || area != null) {
            val displayText: String = when {
                country != null && area != null -> "$country, $area"
                country != null -> "$country"
                else -> "$area"
            }
            binding.workPlaceText.setText(displayText)
            binding.workPlace.setEndIconDrawable(R.drawable.ic_close)
            binding.workPlace.setEndIconOnClickListener {
                viewModel.clearAreaField()
                viewModel.getFilterSettings()
                clearAreaField()
            }
        } else {
            clearAreaField()
        }
    }

    private fun clearIndustryField() {
        binding.industryText.setText("")
        binding.industry.setEndIconDrawable(R.drawable.arrow_forward)
    }

    private fun clearAreaField() {
        binding.workPlaceText.setText("")
        binding.workPlace.setEndIconDrawable(R.drawable.arrow_forward)
    }

    private fun clearFields() {
        clearIndustryField()
        clearAreaField()
    }

    private fun restoreSettingsAndNavBack() {
        viewModel.restoreFilterSettings()
        findNavController().navigateUp()
    }

    private fun initEditTextListener(filterParameters: FilterParameters?) {
        binding.salary.doOnTextChanged { s: CharSequence?, _, _, _ ->
            if (s.isNullOrEmpty()) {
                binding.amountTextLayout.endIconMode = END_ICON_NONE
                binding.btnGroup.isVisible = filterParameters != null
            } else {
                binding.amountTextLayout.endIconMode = END_ICON_CUSTOM
                binding.btnGroup.visibility = View.VISIBLE
                binding.amountTextLayout.setEndIconOnClickListener {
                    binding.salary.setText("")
                    viewModel.saveSalary(null)
                }
            }
        }
    }

}