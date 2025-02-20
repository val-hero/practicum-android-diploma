package ru.practicum.android.diploma.filter.ui.selectRegion.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectRegionBinding
import ru.practicum.android.diploma.filter.ui.selectRegion.adapter.RegionSelectorAdapter
import ru.practicum.android.diploma.filter.ui.selectRegion.viewmodel.SelectRegionViewModel
import ru.practicum.android.diploma.search.domain.models.fields.Area

class SelectRegionFragment : Fragment() {

    private var _binding: FragmentSelectRegionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RegionSelectorAdapter
    private val viewModel by viewModel<SelectRegionViewModel>()
    private val args: SelectRegionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.areas.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.updateRegion(resource.data.map { it })
                    initInputRegion(resource.data.map { it })
                }

                is Resource.Error -> showError()
            }
        }

        binding.regionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = RegionSelectorAdapter(emptyList(), ::onRegionClick)
        binding.regionsRecycler.adapter = adapter

        viewModel.getAreas(args.countryId)

        binding.selectRegionToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initInputRegion(areaList: List<Area>) {
        with(binding) {
            searchRegion.setOnClickListener {
                searchRegion.isCursorVisible = true
            }
            searchRegion.doOnTextChanged { s: CharSequence?, _, _, _ ->
                if (s.isNullOrEmpty()) {
                    editTextImage.setImageResource(R.drawable.ic_search)
                } else {
                    editTextImage.setImageResource(R.drawable.ic_close)
                }
                findArea(binding.searchRegion.text.toString(), areaList)
            }

            searchRegion.requestFocus()

            editTextImage.setOnClickListener {
                clearSearch()
            }
        }
    }

    private fun clearSearch() {
        binding.searchRegion.setText("")
        binding.placeholderError.isVisible = false
        val view = requireActivity().currentFocus
        if (view != null) {
            hideKeyboard()
        }
    }

    private fun showError() {
        with(binding) {
            regionsRecycler.isVisible = false
            placeholderNoList.isVisible = true
        }
    }

    private fun onRegionClick(region: Area) {
        viewModel.saveArea(region)
        findNavController().navigateUp()
    }

    private fun findArea(query: String, areaList: List<Area>) {
        when (query) {
            query.isEmpty().toString() -> adapter.updateRegion(areaList)
            else -> {
                val newList =
                    areaList.filter { it.name!!.contains(query.trim(), ignoreCase = true) }
                adapter.updateRegion(newList)
                binding.placeholderError.isVisible = newList.isEmpty()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun hideKeyboard() {
        val inputManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}