package ru.practicum.android.diploma.core.utils.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ItemSearchBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy

class VacancyViewHolder(
    private val binding: ItemSearchBinding,
    private val onClick: (Vacancy) -> Unit,
    private val onLongClick: (Vacancy) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Vacancy) {
        with(binding) {
            if (model.area?.name?.isNotBlank() == true) {
                val header = buildString {
                    append(model.name)
                    append(", ")
                    append(model.area.name)
                }
                title.text = header
            } else title.text = model.name

            company.text = model.employer?.name
            salary.text = getSalary(model, salary.context)
            root.setOnClickListener { onClick(model) }
            root.setOnLongClickListener {
                onLongClick.invoke(model)
                true
            }

            Glide.with(itemView)
                .load(model.employer?.logoUrls?.smallLogo)
                .placeholder(R.drawable.employer_logo_placeholder)
                .centerCrop()
                .transform(
                    RoundedCorners(
                        itemView.resources.getDimensionPixelSize(
                            R.dimen.corner_radius_12
                        )
                    )
                ).into(logo)
        }
    }

    private fun getSalary(model: Vacancy, context: Context): String {
        return when {
            (model.salary?.currency == null) -> context.getString(R.string.no_salary)

            ((model.salary.to == null) && (model.salary.from == null)) -> context.getString(R.string.no_salary)

            (model.salary.to == null) -> "${context.getString(R.string.from)} ${model.salary.from} ${model.salary.currency}"

            (model.salary.from == null) -> "${context.getString(R.string.to)} ${model.salary.to} ${model.salary.currency}"

            else -> "${context.getString(R.string.from)} ${model.salary.from} " + "${
                context.getString(
                    R.string.to
                )
            } ${model.salary.to} ${model.salary.currency}"
        }
    }
}

