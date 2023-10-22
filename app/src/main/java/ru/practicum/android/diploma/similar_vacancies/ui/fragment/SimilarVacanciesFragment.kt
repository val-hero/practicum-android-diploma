package ru.practicum.android.diploma.similar_vacancies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.ErrorType
import ru.practicum.android.diploma.core.utils.adapter.VacancyAdapter
import ru.practicum.android.diploma.databinding.FragmentSimilarVacanciesBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.similar_vacancies.ui.state.SimilarVacanciesScreenState
import ru.practicum.android.diploma.similar_vacancies.ui.viewmodel.SimilarVacanciesViewModel

class SimilarVacanciesFragment : Fragment() {
    private var _binding: FragmentSimilarVacanciesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SimilarVacanciesViewModel>()
    private val args by navArgs<SimilarVacanciesFragmentArgs>()
    private val adapter = VacancyAdapter(
        onClick = { onVacancyClick(it.id) },
        onLongClick = { true }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimilarVacanciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.similarVacanciesRecycler.adapter = adapter

        viewModel.fetchSimilarVacancies(args.vacancyId)

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            render(state)
        }

        initToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initToolbar() {
        binding.similarVacancyToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun render(screenState: SimilarVacanciesScreenState) {
        when (screenState) {
            is SimilarVacanciesScreenState.Content -> {
                showVacancies(screenState.vacancies)
            }

            is SimilarVacanciesScreenState.Error -> {
                showError(screenState.type)
            }

            SimilarVacanciesScreenState.Loading -> {
                showLoading()
            }
        }
    }

    private fun showVacancies(vacancies: List<Vacancy>) {
        adapter.setVacancies(vacancies)
        binding.similarVacanciesRecycler.isVisible = true
        binding.progressBarForLoad.isVisible = false
        hideErrors()
        binding.placeholderNotFound.isVisible = vacancies.isEmpty()
    }

    private fun hideErrors() {
        binding.placeholderServerError.isVisible = false
        binding.placeholderNoInternet.isVisible = false
        binding.placeholderNotFound.isVisible = false
    }

    private fun showError(type: ErrorType) {
        binding.similarVacanciesRecycler.isVisible = false
        binding.progressBarForLoad.isVisible = false

        when (type) {
            ErrorType.NO_CONNECTION -> binding.placeholderNoInternet.isVisible = true
            ErrorType.NOT_FOUND -> binding.placeholderNotFound.isVisible = true
            else -> binding.placeholderServerError.isVisible = true
        }
    }

    private fun showLoading() {
        hideErrors()
        binding.similarVacanciesRecycler.isVisible = false
        binding.progressBarForLoad.isVisible = true
    }

    private fun onVacancyClick(id: String) {
        if (!viewModel.isClickable) return

        viewModel.onVacancyClick()
        findNavController().navigate(
            SimilarVacanciesFragmentDirections.actionToVacancyDetailsFragment(
                id
            )
        )
    }
}
