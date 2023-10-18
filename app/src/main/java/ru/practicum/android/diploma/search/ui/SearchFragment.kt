package ru.practicum.android.diploma.search.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.MainNavGraphDirections
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.adapter.VacancyAdapter
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.ui.state.SearchScreenState
import ru.practicum.android.diploma.search.ui.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = VacancyAdapter(
        onClick = { onVacancyClick(it.id) },
        onLongClick = { true }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInput()

        initAdapter()

        viewModel.uiState.observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.filterSettingsState.observe(viewLifecycleOwner) {
            when (it) {
                true -> showNoEmptyFilterIcon()
                else -> showEmptyFilterIcon()
            }
        }

        binding.filterIcon.setOnClickListener {
            viewModel.filterSettingsState.observe(viewLifecycleOwner) {
                when (it) {
                    true -> showNoEmptyFilterIcon()
                    else -> showEmptyFilterIcon()
                }
            }
            navToFilter()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateFilterSettings()
        viewModel.cancelDebounce = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        binding.searchRecycler.adapter = adapter
    }

    private fun initInput() {
        binding.inputSearchForm.setOnClickListener {
            binding.inputSearchForm.isCursorVisible = true
        }
        binding.inputSearchForm.doOnTextChanged { s: CharSequence?, _, _, _ ->
            if (s.isNullOrEmpty()) {
                binding.editTextImage.setImageResource(R.drawable.ic_search)
            } else {
                binding.editTextImage.setImageResource(R.drawable.ic_close)
            }

            if (binding.inputSearchForm.hasFocus() && s.toString().isNotEmpty()) {
                showDefault()
            }

            viewModel.searchDebounce(binding.inputSearchForm.text.toString())
        }

        binding.inputSearchForm.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.search(binding.inputSearchForm.text.toString())
            }
            false
        }

        binding.inputSearchForm.requestFocus()

        binding.editTextImage.setOnClickListener {
            clearSearch()
        }
    }

    private fun clearSearch() {
        binding.inputSearchForm.setText("")
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        showDefault()
    }

    private fun render(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.Success -> showVacancies(state.vacancies)
            is SearchScreenState.Loading -> showLoading()
            is SearchScreenState.NothingFound -> showVacancies(state.vacancies)
            is SearchScreenState.Default -> showDefault()
            is SearchScreenState.Error -> showError(state.type)
        }
    }

    private fun showVacancies(vacancies: List<Vacancy>) {
        adapter.setVacancies(vacancies)
        binding.placeholderImage.isVisible = false
        binding.progressBarForLoad.isVisible = false
        binding.placeholderNoInternet.isVisible = false
        binding.textFabSearch.isVisible = true
        binding.placeholderServerError.isVisible = false

        if (vacancies.isEmpty()) {
            binding.textFabSearch.text = resources.getString(R.string.no_vacancies)
            binding.placeholderError.isVisible = true
            binding.searchRecycler.isVisible = false
        } else {
            binding.textFabSearch.text =
                resources.getQuantityString(R.plurals.vacancies, vacancies.size, vacancies.size)
            binding.searchRecycler.isVisible = true
            binding.placeholderError.isVisible = false
        }
    }

    private fun showError(type: ErrorType) {
        binding.placeholderImage.isVisible = false
        binding.placeholderError.isVisible = false
        binding.searchRecycler.isVisible = false
        binding.progressBarForLoad.isVisible = false
        binding.textFabSearch.isVisible = false
        when (type) {
            ErrorType.NO_CONNECTION -> binding.placeholderNoInternet.isVisible = true
            else -> binding.placeholderServerError.isVisible = true
        }
    }

    private fun onVacancyClick(id: String) {
        if (!viewModel.isClickable) return
        viewModel.onVacancyClick()
        findNavController().navigate(MainNavGraphDirections.actionToVacancyDetailsFragment(id))
    }

    private fun showLoading() {
        binding.placeholderImage.isVisible = false
        binding.placeholderError.isVisible = false
        binding.searchRecycler.isVisible = false
        binding.progressBarForLoad.isVisible = true
        binding.textFabSearch.isVisible = false
        binding.placeholderNoInternet.isVisible = false
        binding.placeholderServerError.isVisible = false
    }

    private fun showDefault() {
        binding.searchRecycler.isVisible = false
        binding.progressBarForLoad.isVisible = false
        binding.placeholderImage.isVisible = true
        binding.textFabSearch.isVisible = false
        binding.placeholderError.isVisible = false
        binding.placeholderNoInternet.isVisible = false
        binding.placeholderServerError.isVisible = false
    }

    private fun navToFilter() {
        findNavController().navigate(R.id.action_searchFragment_to_filteringSettingsFragment)
    }

    override fun onPause() {
        super.onPause()
        viewModel.cancelDebounce = true
    }

    private fun showEmptyFilterIcon() {
        binding.filterIcon.setImageResource(R.drawable.ic_filter)
    }

    private fun showNoEmptyFilterIcon() {
        binding.filterIcon.setImageResource(R.drawable.filter_on)
    }

}