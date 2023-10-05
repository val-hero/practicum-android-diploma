package ru.practicum.android.diploma.filter.ui.selectindustry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.databinding.FragmentSelectIndustryBinding
import ru.practicum.android.diploma.filter.IndustryAndRegionSelectorAdapter


class SelectIndustryFragment : Fragment() {

    private var _binding: FragmentSelectIndustryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: IndustryAndRegionSelectorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.industryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = IndustryAndRegionSelectorAdapter(emptyList(), ::onIndustryClick)
        binding.industryRecycler.adapter = adapter
    }

    private fun onIndustryClick(industryName: String) {
        Toast.makeText(requireContext(), "Клац", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}