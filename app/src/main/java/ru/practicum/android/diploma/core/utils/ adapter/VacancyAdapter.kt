package ru.practicum.android.diploma.core.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.search.domain.models.Vacancy


class VacancyAdapter(private val vacancies: ArrayList<Vacancy>) :
    RecyclerView.Adapter<VacancyViewHolder>() {

    var itemClickListener: ((Int, Vacancy) -> Unit)? = null
    var itemLongClickListener: ((Int, Vacancy) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.bind(vacancy)
        holder.itemView.setOnClickListener() {
            itemClickListener?.invoke(position, vacancy)
        }

        holder.itemView.setOnLongClickListener(){
            itemLongClickListener?.invoke(position, vacancy)
            return@setOnLongClickListener true
        }
    }

    fun setVacancies(newVacancy: List<Vacancy>?) {
        vacancies.clear()
        if (!newVacancy.isNullOrEmpty()) {
            vacancies.addAll(newVacancy)
        }
        notifyDataSetChanged()
    }
}

class VacancyViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parentView.context)
        .inflate(R.layout.item_search, parentView, false)
) {
    private val logo: ImageView = itemView.findViewById(R.id.image)
    private val name: TextView = itemView.findViewById(R.id.title)
    private val employerName: TextView = itemView.findViewById(R.id.company)
    private val salary: TextView = itemView.findViewById(R.id.salary)

    fun bind(model: Vacancy) {
        name.text = "${model.name}, ${model.area}"
        employerName.text = model.employer.toString()
        salary.text = model.salary.toString()

        Glide.with(itemView)
            .load(model.employer?.logoUrls?.smallLogo)
            .placeholder(R.drawable.placeholder)
            .into(logo)
    }
}

