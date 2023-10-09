package ru.practicum.android.diploma.favorites.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.adapter.VacancyAdapter
import ru.practicum.android.diploma.databinding.FragmentFavoritesBinding
import ru.practicum.android.diploma.favorites.ui.state.FavoritesScreenState
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoritesFragmentViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModel<FavoritesFragmentViewModel>()
    private val adapter = VacancyAdapter(
        onClick = {  },
        onLongClick = { true }
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

    private fun render(state: FavoritesScreenState) {
        when (state) {
            is FavoritesScreenState.Empty -> {
                with(binding) {
                    favouritesRecycler.visibility = View.GONE
                    placeholder.visibility = View.VISIBLE
                }
            }

            is FavoritesScreenState.FavoritesVacancies -> {
                with(binding) {
                    favouritesRecycler.visibility = View.VISIBLE
                    placeholder.visibility = View.GONE
                }
            }
        }
    }

    private fun initAdapter() {
        binding.favouritesRecycler.adapter = adapter
    }

//    private fun clickOnVacancy(vacancy: Vacancy) {
//        if (!viewModel.isClickable) return
//        viewModel.onVacancyClick()
//        findNavController().navigate(
//            R.id.action_to_VacancyDetailsFragment,
//            Bundle().apply {
//                putSerializable(Constants.VACANCY, vacancy)
//            }
//        )
//    }

}