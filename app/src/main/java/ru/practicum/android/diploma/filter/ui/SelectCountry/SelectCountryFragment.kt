package ru.practicum.android.diploma.filter.ui.SelectCountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.databinding.FragmentSelectCountryBinding

class SelectCountryFragment : Fragment() {

    private var _binding: FragmentSelectCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SelectCountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //моковый список стран
        val countries = arrayOf(
            "Россия",
            "Украина",
            "Казахстан",
            "Азербайджан",
            "Беларусь",
            "Грузия",
            "Кыргыстан",
            "Узбекистан",
            "Другие регионы"
        )

        binding.countryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = SelectCountryAdapter(countries)
        binding.countryRecycler.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
