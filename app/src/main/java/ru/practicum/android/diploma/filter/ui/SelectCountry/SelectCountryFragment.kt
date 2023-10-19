package ru.practicum.android.diploma.filter.ui.SelectCountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectCountryBinding
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.ui.SelectCountry.viewmodel.SelectCountryViewModel

class SelectCountryFragment : Fragment() {

    private var _binding: FragmentSelectCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SelectCountryAdapter
    private val viewModel by viewModel<SelectCountryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.countries.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.updateCountries(resource.data.map { it })
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
            }
        }

        binding.countryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = SelectCountryAdapter(emptyList(), ::onCountryClick)
        binding.countryRecycler.adapter = adapter

        viewModel.getCountries()

        binding.searchToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onCountryClick(country: Country) {
        viewModel.saveCountry(country)
        findNavController().popBackStack()
    }
}
