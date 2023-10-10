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
import ru.practicum.android.diploma.databinding.FragmentWorkplaceBinding
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
            if(it != null) {
                binding.country.hint = it.country?.name as String
                it.area?.name?.let { binding.region.hint = it }
            }
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

    override fun onResume() {
        super.onResume()
        viewModel.getFilterSettings()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}