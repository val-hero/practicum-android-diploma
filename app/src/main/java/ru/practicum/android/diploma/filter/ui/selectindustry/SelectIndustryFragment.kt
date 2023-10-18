package ru.practicum.android.diploma.filter.ui.selectindustry

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
import ru.practicum.android.diploma.databinding.FragmentSelectIndustryBinding
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.filter.ui.selectindustry.viewmodel.SelectIndustryViewModel


class SelectIndustryFragment : Fragment() {

    private var _binding: FragmentSelectIndustryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: IndustrySelectorAdapter
    private val viewModel by viewModel<SelectIndustryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.industry.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {

                    adapter.updateIndustry(resource.data.map { it })
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
            }
        }

        binding.industryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = IndustrySelectorAdapter(emptyList(), ::onIndustryClick)
        binding.industryRecycler.adapter = adapter

        viewModel.getIndustry()

        initInputIndustry()

        initToolbar()

    }

    private fun initToolbar() {
        binding.selectIndustryToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initInputIndustry() {
        with(binding) {
            searchIndustry.setOnClickListener {
                searchIndustry.isCursorVisible = true
            }
            searchIndustry.doOnTextChanged { s: CharSequence?, _, _, _ ->
                if (s.isNullOrEmpty()) {
                    editTextImage.setImageResource(R.drawable.ic_search)
                } else {
                    editTextImage.setImageResource(R.drawable.ic_close)
                }

                if (binding.searchIndustry.hasFocus() && s.toString().isNotEmpty()) {
                    //todo
                }
                //todo debounce(?)
            }

            searchIndustry.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //todo search
                }
                false
            }
            searchIndustry.requestFocus()

            editTextImage.setOnClickListener {
                clearSearch()
            }
        }
    }

    private fun clearSearch() {
        binding.searchIndustry.setText("")
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun onIndustryClick(industry: Industry) {
        if (!industry.industries.isNullOrEmpty()) {
            adapter.updateIndustry(industry.industries)
        }
        viewModel.saveIndustry(industry)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}