package ru.practicum.android.diploma.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentWorkplaceBinding
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.ui.SelectWorkplaceViewModel

class SelectWorkplaceFragment : Fragment() {

    private var _binding: FragmentWorkplaceBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SelectWorkplaceViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.updateFilterSettings().observe(viewLifecycleOwner) {
            render(it)
        }

        binding.countryText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                findNavController().navigate(R.id.action_selectWorkplaceFragment_to_selectCountryFragment)
            }
        }
        binding.regionText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                findNavController().navigate(R.id.action_selectWorkplaceFragment_to_selectRegionFragment)
            }
        }

        binding.workplaceToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun render(it: FilterParameters?) {
        if (it?.country != null) {
            binding.countryText.setText(it.country?.name)
            binding.country.setEndIconDrawable(R.drawable.ic_close)
            binding.country.setEndIconOnClickListener {
                viewModel.clearCountryField()
                binding.countryText.setText("")
                binding.country.setEndIconDrawable(R.drawable.arrow_forward)
            }
        } else {
            binding.countryText.setText("")
            binding.country.setEndIconDrawable(R.drawable.arrow_forward)
        }
        if (it?.area != null) {
            binding.regionText.setText(it.area?.name)
            binding.region.setEndIconDrawable(R.drawable.ic_close)
            binding.region.setEndIconOnClickListener {
                viewModel.clearAreaField()
                binding.regionText.setText("")
                binding.region.setEndIconDrawable(R.drawable.arrow_forward)
            }
        } else {
            binding.regionText.setText("")
            binding.region.setEndIconDrawable(R.drawable.arrow_forward)
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
}