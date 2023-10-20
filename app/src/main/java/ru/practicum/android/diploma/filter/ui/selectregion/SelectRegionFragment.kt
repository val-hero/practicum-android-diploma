package ru.practicum.android.diploma.filter.ui.selectregion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectRegionBinding
import ru.practicum.android.diploma.filter.ui.selectregion.viewmodel.SelectRegionViewModel
import ru.practicum.android.diploma.search.domain.models.fields.Area

class SelectRegionFragment : Fragment() {

    private var _binding: FragmentSelectRegionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RegionSelectorAdapter
    private val viewModel by viewModel<SelectRegionViewModel>()
    private val args: SelectRegionFragmentArgs by navArgs()
    private lateinit var regionList: List<Area?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()

        viewModel.areas.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.updateRegion(resource.data.map { it })
                    regionList = resource.data.map { it }
                    initInputRegion()
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
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

    private fun initInputRegion() {
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
                findArea(binding.searchRegion.text.toString())
            }

            searchRegion.requestFocus()

            editTextImage.setOnClickListener {
                clearSearch()
            }
        }
    }

    private fun clearSearch() {
        binding.searchRegion.setText("")
        val view = requireActivity().currentFocus
        if (view != null) {
            hideKeyboard()
        }
    }

    private fun onRegionClick(region: Area) {
        viewModel.saveArea(region)
        findNavController().popBackStack()
    }

    private fun findArea(query: String) {
        when (query) {
            "" -> adapter.updateRegion(regionList)
            else -> {
                val newList =
                    regionList.filter { it?.name!!.contains(query.trim(), ignoreCase = true) }
                adapter.updateRegion(newList)
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