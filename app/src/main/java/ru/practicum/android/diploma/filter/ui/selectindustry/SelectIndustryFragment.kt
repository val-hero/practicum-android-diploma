package ru.practicum.android.diploma.filter.ui.selectindustry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.databinding.FragmentSelectIndustryBinding
import ru.practicum.android.diploma.filter.IndustryAndRegionSelectorAdapter
import ru.practicum.android.diploma.filter.ui.selectindustry.viewmodel.SelectIndustryViewModel


class SelectIndustryFragment : Fragment() {

    private var _binding: FragmentSelectIndustryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: IndustryAndRegionSelectorAdapter
    private val viewModel by viewModel<SelectIndustryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectIndustryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.industry.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {

                    adapter.updateRegionOrIndustry(resource.data.map { it.name })
                }

                is Resource.Error -> {
                    // Показать ошибку
                }
            }
        }

        binding.industryRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = IndustryAndRegionSelectorAdapter(emptyList(), ::onIndustryClick)
        binding.industryRecycler.adapter = adapter

        viewModel.getIndustry()

        binding.selectIndustryToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun onIndustryClick(industryName: String) {
        Toast.makeText(requireContext(), "Клац", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}