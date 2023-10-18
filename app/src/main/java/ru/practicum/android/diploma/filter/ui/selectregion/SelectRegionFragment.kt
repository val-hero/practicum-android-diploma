package ru.practicum.android.diploma.filter.ui.selectregion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
            }
        }

        binding.regionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = RegionSelectorAdapter(emptyList(), ::onRegionClick)
        binding.regionsRecycler.adapter = adapter

        viewModel.getAreas()

        binding.selectRegionToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        initInputRegion()

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

                if (binding.searchRegion.hasFocus() && s.toString().isNotEmpty()) {
                    //todo
                }
                //todo debounce(?)
            }

            searchRegion.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //todo search
                }
                false
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
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun onRegionClick(region: Area) {
        if (!region.areas.isNullOrEmpty()) {
            adapter.updateRegion(region.areas)
        }
        viewModel.saveArea(region)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}