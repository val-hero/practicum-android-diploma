package ru.practicum.android.diploma.core.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ItemSearchBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy


class VacancyAdapter(
    private val onClick: (Vacancy) -> Unit,
    private val onLongClick: (Vacancy) -> Boolean
) :
    RecyclerView.Adapter<VacancyViewHolder>() {

    val vacancies = ArrayList<Vacancy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return VacancyViewHolder(
            ItemSearchBinding.inflate(layoutInflater, parent, false),
            onClick, onLongClick
        )
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    fun setVacancies(newVacancies: List<Vacancy>?) {
        vacancies.clear()
        if (!newVacancies.isNullOrEmpty()) {
            vacancies.addAll(newVacancies)
        }
        notifyDataSetChanged()
    }
}

class VacancyViewHolder(
    private val binding: ItemSearchBinding,
    private val onClick: (Vacancy) -> Unit,
    private val onLongClick: (Vacancy) -> Boolean
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Vacancy) {
        with(binding) {
            title.text = "${model.name}, ${model.area.name}"
            company.text = model.employer.name
            salary.text = getSalary(model, salary.context)
            root.setOnClickListener { onClick(model) }
            root.setOnLongClickListener { onLongClick(model) }

            Glide.with(itemView)
                .load(model.employer.logoUrls?.smallLogo)
                .placeholder(R.drawable.placeholder)
                .into(logo)
        }
    }

    private fun getSalary(model: Vacancy, context: Context): String {
        return when {
            (model.salary?.currency == null) ->
                context.getString(R.string.no_salary)

            (((model.salary.to == null) && (model.salary.from == null))) ->
                context.getString(R.string.no_salary)

            (model.salary.to == null) ->
                "${context.getString(R.string.from)} ${model.salary.from} ${model.salary.currency}"

            (model.salary.from == null) ->
                "${context.getString(R.string.to)} ${model.salary.to} ${model.salary.currency}"

            else ->
                "${context.getString(R.string.from)} ${model.salary.from} " +
                        "${context.getString(R.string.to)} ${model.salary.to} ${model.salary.currency}"
        }
    }
}

