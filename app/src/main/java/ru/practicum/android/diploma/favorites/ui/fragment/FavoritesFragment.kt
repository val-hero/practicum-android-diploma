package ru.practicum.android.diploma.favorites.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.MainNavGraphDirections
import ru.practicum.android.diploma.core.utils.adapter.VacancyAdapter
import ru.practicum.android.diploma.databinding.FragmentFavoritesBinding
import ru.practicum.android.diploma.favorites.ui.state.FavoritesScreenState
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoritesFragmentViewModel
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.toVacancy

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModel<FavoritesFragmentViewModel>()
    private val adapter = VacancyAdapter(
        onClick = { onVacancyClick(id = it.id) },
        onLongClick = { openDeleteVacancyDialog(id=it.id) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeContentState().observe(viewLifecycleOwner) {
            render(it)
        }

        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoritesVacancies()
    }

    private fun render(state: FavoritesScreenState) {
        when (state) {
            is FavoritesScreenState.Empty ->  showEmpty()
            is FavoritesScreenState.FavoritesVacancies -> showList(state.vacancies.map { it.toVacancy() })
            else -> showError()
        }
    }

    private fun initAdapter() {
        binding.favouritesRecycler.adapter = adapter
    }

    private fun showList(vacancies: List<Vacancy>) {
        with(binding) {
            favouritesRecycler.visibility = View.VISIBLE
            adapter.setVacancies(vacancies)
            placeholderEmpty.visibility = View.GONE
            placeholderError.visibility = View.GONE
        }
    }

    private fun showEmpty() {
        with(binding) {
            favouritesRecycler.visibility = View.GONE
            placeholderEmpty.visibility = View.VISIBLE
            placeholderError.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            favouritesRecycler.visibility = View.GONE
            placeholderEmpty.visibility = View.GONE
            placeholderError.visibility = View.VISIBLE
        }
    }


    private fun onVacancyClick(id: String) {
        if (!viewModel.isClickable) return
        viewModel.onVacancyClick()
        findNavController().navigate(MainNavGraphDirections.actionToVacancyDetailsFragment(id))
    }

    private fun openDeleteVacancyDialog(id: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Удалить вакансию")
            .setMessage("Вы хотите удалить вакансию ?")
            .setNegativeButton("Нет") { dialog, which ->
                Toast.makeText(requireContext(),"нажали нет",Toast.LENGTH_LONG).show()
            }
            .setPositiveButton("Да") { dialog, which ->
                Toast.makeText(requireContext(),"\"нажали да\"",Toast.LENGTH_LONG).show()

            }
            .show()
    }

}