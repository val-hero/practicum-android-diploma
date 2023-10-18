package ru.practicum.android.diploma.filter.ui.selectregion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectRegionBinding
import ru.practicum.android.diploma.filter.ui.selectregion.viewmodel.SelectRegionViewModel
import ru.practicum.android.diploma.search.domain.models.fields.Area

class SelectRegionFragment : Fragment() {

    private var _binding: FragmentSelectRegionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RegionSelectorAdapter
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
                    adapter.updateRegion(getFlatAreaList(resource.data).map { it })
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
            }
        }

        binding.regionsRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter= RegionSelectorAdapter(emptyList(), ::onRegionClick)
        binding.regionsRecycler.adapter = adapter

        viewModel.getAreas()

        binding.selectRegionToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getFlatAreaList(areaList: List<Area>): List<Area> {
        val flatAreaList = arrayListOf<Area>()

        fun flatten(area: Area) {
            flatAreaList.add(area)
            area.areas?.let { area.areas.forEach { flatten(it) } }
        }

        areaList.forEach { flatten(it) }
        val listWithoutCountry = flatAreaList.filter { it.countryId != null }
        return listWithoutCountry.sortedBy { it.name }
    }

    private fun onRegionClick(region: Area) {
        viewModel.saveArea(region)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}