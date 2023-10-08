package ru.practicum.android.diploma.search.ui

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.adapter.VacancyAdapter
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.ui.state.SearchScreenState
import ru.practicum.android.diploma.search.ui.viewmodel.SearchViewModel
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.search.domain.models.Vacancy


class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = VacancyAdapter(
        onClick = { clickOnVacancy(it) },
        onLongClick = { true }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchRecycler?.adapter = adapter

        initInput()

        viewModel.uiState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun initInput() {
        binding?.inputSearchForm?.setOnClickListener {
            binding?.inputSearchForm?.isCursorVisible = true
        }
        binding?.inputSearchForm?.doOnTextChanged { s: CharSequence?, _, _, _ ->
            binding?.editTextSearchImage?.visibility = View.GONE
            binding?.buttonClearSearch?.visibility = clearButtonVisibility(s)

            viewModel.searchDebounce(binding?.inputSearchForm?.text.toString())
        }

        binding?.inputSearchForm?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.search(binding?.inputSearchForm?.text.toString())
            }
            false
        }

        binding?.buttonClearSearch?.visibility =
            clearButtonVisibility(binding?.inputSearchForm?.text)

        binding?.inputSearchForm?.requestFocus()

        binding?.buttonClearSearch?.setOnClickListener {
            clearSearch()
        }
    }

    private fun clearSearch() {
        binding?.inputSearchForm?.setText("")
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        showPlaceholder()
    }

    private fun clearButtonVisibility(s: CharSequence?): Int {
        return if (s.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun clickOnVacancy(vacancy: Vacancy) {
        if (!viewModel.isClickable) return
        viewModel.onVacancyClick()
        findNavController().navigate(R.id.action_to_VacancyFragment)
        Bundle().apply {
            putSerializable(Constants.VACANCY, vacancy)
        }
    }

    private fun render(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Success -> {
                adapter.setVacancies(state.vacancies)
                showVacancies(state.foundValue)
            }

            is SearchScreenState.Error -> showError()
            is SearchScreenState.Loading -> showLoading()
            is SearchScreenState.NothingFound -> showNotFound()
            is SearchScreenState.Default -> showPlaceholder()
        }
    }

    private fun showVacancies(foundValue: Int) {
        binding?.placeholderImage?.isVisible = false
        binding?.searchRecycler?.isVisible = true
        binding?.progressBarForLoad?.isVisible = false
        binding?.textFabSearch?.isVisible = true
        binding?.textFabSearch?.text =
            resources.getQuantityString(R.plurals.vacancies, foundValue, foundValue)
    }

    private fun showError() {
        binding?.placeholderImage?.isVisible = false
        binding?.searchRecycler?.isVisible = false
        binding?.progressBarForLoad?.isVisible = false
        binding?.textFabSearch?.isVisible = true
        binding?.textFabSearch?.text = context?.getString(R.string.server_error)

    }

    private fun showLoading() {
        binding?.placeholderImage?.isVisible = false
        binding?.searchRecycler?.isVisible = false
        binding?.progressBarForLoad?.isVisible = true
        binding?.textFabSearch?.isVisible = false
    }

    private fun showPlaceholder() {
        binding?.searchRecycler?.isVisible = false
        binding?.progressBarForLoad?.isVisible = false
        binding?.placeholderImage?.isVisible = true
        binding?.textFabSearch?.isVisible = false
    }

    private fun showNotFound() {
        binding?.searchRecycler?.isVisible = false
        binding?.progressBarForLoad?.isVisible = false
        binding?.placeholderImage?.isVisible = false
        binding?.textFabSearch?.isVisible = true
        binding?.textFabSearch?.text = context?.getString(R.string.no_vacancies)
    }

    private fun showEmptyFilterIcon() {
        binding?.filterIcon?.setImageResource(R.drawable.ic_filter)
        //TODO
    }

    private fun showNoEmptyFilterIcon() {
        binding?.filterIcon?.setImageResource(R.drawable.filter_on)
        //TODO
    }

}