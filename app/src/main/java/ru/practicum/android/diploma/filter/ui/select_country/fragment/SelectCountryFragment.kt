package ru.practicum.android.diploma.filter.ui.select_country.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectCountryBinding
import ru.practicum.android.diploma.filter.domain.models.fields.Country
import ru.practicum.android.diploma.filter.ui.select_country.adapter.SelectCountryAdapter
import ru.practicum.android.diploma.filter.ui.select_country.viewmodel.SelectCountryViewModel

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

        hideKeyboard()

        viewModel.countries.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.updateCountries(resource.data.map { it })
                }

                is Resource.Error -> showError()

            }
        }

        binding.countryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = SelectCountryAdapter(emptyList(), ::onCountryClick)
        binding.countryRecycler.adapter = adapter

        viewModel.getCountries()

        binding.countryToolbar.setNavigationOnClickListener {
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

    private fun showError() {
        binding.countryRecycler.visibility = View.GONE
        binding.placeholderNoListCountry.visibility = View.VISIBLE
    }
    private fun hideKeyboard() {
        val inputManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}
