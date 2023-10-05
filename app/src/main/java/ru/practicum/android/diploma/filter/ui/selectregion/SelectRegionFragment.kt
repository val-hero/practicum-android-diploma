package ru.practicum.android.diploma.filter.ui.selectregion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectRegionBinding
import ru.practicum.android.diploma.filter.IndustryAndRegionSelectorAdapter
import ru.practicum.android.diploma.filter.ui.selectregion.viewmodel.SelectRegionViewModel

class SelectRegionFragment : Fragment() {

    private var _binding: FragmentSelectRegionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: IndustryAndRegionSelectorAdapter
    private val viewModel by viewModel<SelectRegionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.areas.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {

                    adapter.updateRegionOrIndustry(resource.data.map { it.name })
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
            }
        }

        binding.regionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter= IndustryAndRegionSelectorAdapter(emptyList(), ::onRegionClick)
        binding.regionsRecycler.adapter = adapter

        viewModel.getAreas()
    }

    private fun onRegionClick(regionName: String) {
        Toast.makeText(requireContext(), "Клац", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}