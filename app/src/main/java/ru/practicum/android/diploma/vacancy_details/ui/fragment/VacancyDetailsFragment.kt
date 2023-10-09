package ru.practicum.android.diploma.vacancy_details.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentVacancyDetailsBinding
import ru.practicum.android.diploma.search.domain.models.VacancyDetails
import ru.practicum.android.diploma.search.domain.models.fields.KeySkills
import ru.practicum.android.diploma.search.domain.models.fields.Phones
import ru.practicum.android.diploma.search.domain.models.fields.Salary
import ru.practicum.android.diploma.vacancy_details.ui.state.VacancyDetailsScreenState
import ru.practicum.android.diploma.vacancy_details.ui.viewmodel.VacancyDetailsViewModel

class VacancyDetailsFragment : Fragment() {
    private var binding: FragmentVacancyDetailsBinding? = null
    private val viewModel by viewModel<VacancyDetailsViewModel>()
    private val args: VacancyDetailsFragmentArgs by navArgs()
    private lateinit var vacancy: VacancyDetails


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVacancyDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDetails(args.vacancyId)

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            render(state)
        }

        viewModel.observeFavoriteState().observe(viewLifecycleOwner) {
            renderLikeButton(it)
        }

        viewModel.isFavorite(vacancy.id)

        binding?.addToFavoriteButton?.setOnClickListener { button ->
            (button as? ImageView)?.let { startAnimation(it) }
            viewModel.onFavoriteButtonClick(vacancy)
        }
    }

    private fun renderLikeButton(isFavorite: Boolean) {
        val imageResource = if (isFavorite) R.drawable.favorites_on
        else R.drawable.favorites
        binding?.addToFavoriteButton?.setImageResource(imageResource)
    }

    private fun startAnimation(button: ImageView) {
        button.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.scale
            )
        )
    }

    private fun render(screenState: VacancyDetailsScreenState) {
        when (screenState) {
            is VacancyDetailsScreenState.Content -> {
                fillViews(screenState.vacancyDetails)
                binding?.progressBarForLoad?.isVisible = false
            }

            is VacancyDetailsScreenState.Error -> {

            }

            is VacancyDetailsScreenState.Loading -> {
                showLoading()
            }
        }
    }

    private fun fillViews(vacancy: VacancyDetails) {
        with(binding!!) {
            vacancyName.text = vacancy.name
            area.text = vacancy.area?.name
            salary.text = getSalaryText(vacancy.salary, requireContext())
            Glide.with(this@VacancyDetailsFragment)
                .load(vacancy.employer?.logoUrls?.mediumLogo)
                .placeholder(R.drawable.employer_logo_placeholder)
                .into(employerLogo)

            companyName.text = vacancy.employer?.name
            experience.text = vacancy.experience?.name
            scheduleEmployment.text = vacancy.schedule?.name
            description.text = Html.fromHtml(vacancy.description, Html.FROM_HTML_MODE_COMPACT)
            keySkills.text = getKeySkillsText(vacancy.keySkills)
            contactsName.text = vacancy.contacts?.name
            contactsEmail.text = vacancy.contacts?.email
            contactsPhone.text = getPhonesText(vacancy.contacts?.phones)
            contactsComment.text = getPhonesCommentsText(vacancy.contacts?.phones)

            hideEmptyViews(vacancy)
        }
    }

    private fun showLoading() {
        binding?.progressBarForLoad?.isVisible = true
    }

    private fun getSalaryText(salary: Salary?, context: Context): String {
        return when {
            (salary?.currency == null) -> context.getString(R.string.no_salary)

            (((salary.to == null) && (salary.from == null))) -> context.getString(R.string.no_salary)

            (salary.to == null) -> "${context.getString(R.string.from)} ${salary.from} ${salary.currency}"

            (salary.from == null) -> "${context.getString(R.string.to)} ${salary.to} ${salary.currency}"

            else -> "${context.getString(R.string.from)} ${salary.from} " + "${context.getString(R.string.to)} ${salary.to} ${salary.currency}"
        }
    }

    private fun getKeySkillsText(keySkills: List<KeySkills>?): String {
        var text = ""
        keySkills?.forEach { text += "${it.name}\n" }
        return text
    }

    private fun getPhonesText(phones: List<Phones>?): String {
        var text = ""
        phones?.forEach { text += "+${it.country} (${it.city}) ${it.number}\n" }
        return text.trimEnd()
    }

    private fun getPhonesCommentsText(phones: List<Phones>?): String {
        var text = ""
        phones?.forEach { text += "${it.comment}\n" }
        return text.trimEnd()
    }

    private fun hideEmptyViews(vacancy: VacancyDetails?) {
        with(binding!!) {
            contactsGroup.isVisible = vacancy?.contacts != null
            contactsEmailGroup.isVisible = !vacancy?.contacts?.email.isNullOrBlank()
            contactsPhoneGroup.isVisible = vacancy?.contacts?.phones?.isNotEmpty() ?: false
            contactsCommentGroup.isVisible = !vacancy?.contacts?.phones?.firstOrNull()?.comment.isNullOrBlank()
            contactsPersonGroup.isVisible = !vacancy?.contacts?.name.isNullOrBlank()
            keySkillsGroup.isVisible = !vacancy?.keySkills.isNullOrEmpty()
        }
    }
}