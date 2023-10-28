package ru.practicum.android.diploma.filter.ui.selectIndustry

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectIndustryBinding
import ru.practicum.android.diploma.filter.domain.models.fields.Industry
import ru.practicum.android.diploma.filter.ui.selectIndustry.adapter.IndustrySelectorAdapter
import ru.practicum.android.diploma.filter.ui.selectIndustry.viewmodel.SelectIndustryViewModel


class SelectIndustryFragment : Fragment() {

    private var _binding: FragmentSelectIndustryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: IndustrySelectorAdapter
    private val viewModel by viewModel<SelectIndustryViewModel>()
    private var myIndustry: Industry? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()

        viewModel.industry.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.updateIndustry(getSortedIndustryList(resource.data).map { it })
                }

                is Resource.Error -> showError()
            }
        }

        binding.industryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = IndustrySelectorAdapter(emptyList(), ::onIndustryClick, binding.searchIndustry)
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                val selectedIndustryPosition = adapter.getSelectedPosition()
                if (selectedIndustryPosition != RecyclerView.NO_POSITION) {
                    binding.industryRecycler.scrollToPosition(selectedIndustryPosition)
                }
            }
        })

        binding.industryRecycler.adapter = adapter

        viewModel.getIndustry()

        initInputIndustry()

        initToolbar()

        binding.chooseButton.setOnClickListener {
            viewModel.saveIndustry(myIndustry!!)
            findNavController().navigateUp()
        }
    }

    private fun initToolbar() {
        binding.selectIndustryToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showError() {
        with(binding) {
            industryRecycler.isVisible = false
            placeholderError.isVisible = true
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
            hideKeyboard()
        }
    }

    private fun onIndustryClick(industry: Industry) {
        binding.chooseButton.isVisible = true
        myIndustry = industry
    }

    private fun getSortedIndustryList(industryList: List<Industry>): List<Industry> {
        val industries: ArrayList<Industry> = arrayListOf()

        fun flatten(industry: Industry) {
            industries.add(industry)
            industry.industries?.forEach { flatten(it) }
        }
        industryList.forEach {
            flatten(it)
        }
        industries.sortBy { it.name }
        return industries
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