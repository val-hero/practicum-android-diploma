package ru.practicum.android.diploma.core.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemSearchBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy


class VacancyAdapter(
    private val onClick: (Vacancy) -> Unit,
    private val onLongClick: (Vacancy) -> Unit
) :
    RecyclerView.Adapter<VacancyViewHolder>() {

    val vacancies = ArrayList<Vacancy>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): VacancyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return VacancyViewHolder(
            ItemSearchBinding.inflate(
                layoutInflater,
                parent, false
            ),
            onClick,
            onLongClick
        )
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    fun setVacancies(newVacancies: List<Vacancy>?) {
        val diffCallback = VacancyDiffCallback(vacancies, newVacancies ?: emptyList())
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        vacancies.clear()
        if (!newVacancies.isNullOrEmpty()) {
            vacancies.addAll(newVacancies)
        }
        diffResult.dispatchUpdatesTo(this)
    }
}
