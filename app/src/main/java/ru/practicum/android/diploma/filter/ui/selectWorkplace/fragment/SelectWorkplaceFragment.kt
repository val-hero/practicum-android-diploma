package ru.practicum.android.diploma.filter.ui.selectWorkplace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentWorkplaceBinding
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.ui.selectWorkplace.viewmodel.SelectWorkplaceViewModel

class SelectWorkplaceFragment : Fragment() {

    private var _binding: FragmentWorkplaceBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SelectWorkplaceViewModel>()
    private var countryId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filterSettings.observe(viewLifecycleOwner) {
            render(it)
        }

        binding.countryText.setOnClickListener {
            findNavController().navigate(R.id.action_selectWorkplaceFragment_to_selectCountryFragment)
        }
        binding.regionText.setOnClickListener {
            navigateToRegion(countryId)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            clearInformationOnWorkPlace()
            restoreSettingsAndNavBack()
        }

        binding.workplaceToolbar.setNavigationOnClickListener {
            restoreSettingsAndNavBack()
        }

        binding.countryText.doOnTextChanged { s: CharSequence?, _, _, _ ->
            binding.chooseButton.isVisible = !s.isNullOrEmpty()
            checkInformationOnWorkPlace()
        }

        binding.regionText.doOnTextChanged { s: CharSequence?, _, _, _ ->
            binding.chooseButton.isVisible = !s.isNullOrEmpty()
            checkInformationOnWorkPlace()
        }

        binding.chooseButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun render(filter: FilterParameters?) {
        if (filter?.country != null) {
            binding.countryText.setText(filter.country?.name)
            binding.country.setEndIconDrawable(R.drawable.ic_close)
            countryId = filter.country?.id
            binding.country.setEndIconOnClickListener {
                viewModel.clearCountryField()
                binding.countryText.setText("")
                binding.country.setEndIconDrawable(R.drawable.arrow_forward)
                initCountryButtonNavigationListener()
            }
        } else {
            binding.countryText.setText("")
            binding.country.setEndIconDrawable(R.drawable.arrow_forward)
            initCountryButtonNavigationListener()
        }
        if (filter?.area != null) {
            binding.regionText.setText(filter.area?.name)
            binding.region.setEndIconDrawable(R.drawable.ic_close)
            binding.region.setEndIconOnClickListener {
                viewModel.clearAreaField()
                binding.regionText.setText("")
                binding.region.setEndIconDrawable(R.drawable.arrow_forward)
                initRegionButtonNavigationListener()
            }
        } else {
            binding.regionText.setText("")
            binding.region.setEndIconDrawable(R.drawable.arrow_forward)
            initRegionButtonNavigationListener()
        }
    }

    private fun checkInformationOnWorkPlace() {
        val countryText = binding.countryText.text.toString()
        val regionText = binding.regionText.text.toString()
        binding.chooseButton.isVisible = countryText.isNotEmpty() || regionText.isNotEmpty()
    }

    private fun clearInformationOnWorkPlace() {
        viewModel.clearAreaField()
        viewModel.clearCountryField()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFilterSettings()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navigateToRegion(countryId: String?) {
        findNavController().navigate(
            SelectWorkplaceFragmentDirections.actionSelectWorkplaceFragmentToSelectRegionFragment(
                countryId
            )
        )
    }

    private fun navigateToCountry() {
        findNavController().navigate(SelectWorkplaceFragmentDirections.actionSelectWorkplaceFragmentToSelectCountryFragment())
    }

    private fun restoreSettingsAndNavBack() {
        viewModel.restoreFilterSettings()
        clearInformationOnWorkPlace()
        findNavController().popBackStack()
    }

    private fun initCountryButtonNavigationListener() {
        binding.country.setEndIconOnClickListener {
            navigateToCountry()
        }
    }

    private fun initRegionButtonNavigationListener() {
        binding.region.setEndIconOnClickListener {
            navigateToRegion(countryId)
        }
    }
}
