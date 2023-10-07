package ru.practicum.android.diploma.vacancy.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.search.domain.models.Vacancy

class VacancyFragment : Fragment() {

    private lateinit var vacancy: Vacancy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vacancy, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        vacancy = arguments?.getSerializable(Constants.VACANCY) as Vacancy
    }


}