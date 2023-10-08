package ru.practicum.android.diploma.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.adapter.VacancyAdapter
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.search.ui.state.SearchScreenState


class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = VacancyAdapter(
        onClick = { },
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

        binding?.inputSearchForm?.doOnTextChanged { text, _, _, _ ->
            viewModel.search(text.toString())
        }

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchScreenState.Success -> {
                    adapter.setVacancies(state.vacancies)
                    showVacancies()
                }

                is SearchScreenState.Error -> TODO()
                SearchScreenState.Loading -> TODO()
                SearchScreenState.NothingFound -> TODO()
                SearchScreenState.SearchStringEmpty -> TODO()
            }
        }

        binding?.filterIcon?.setOnClickListener {
            navToFilter()
        }
    }

    private fun showVacancies() {
        binding?.placeholderImage?.isVisible = false
        binding?.searchRecycler?.isVisible = true
    }

    private fun navToFilter() {
        findNavController().navigate(R.id.action_searchFragment_to_filteringSettingsFragment)
    }
}