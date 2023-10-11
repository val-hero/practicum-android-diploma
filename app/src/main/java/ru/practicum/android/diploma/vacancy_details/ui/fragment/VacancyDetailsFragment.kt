package ru.practicum.android.diploma.vacancy_details.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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

        binding?.addToFavoriteButton?.setOnClickListener { button ->
            (button as? ImageView)?.let { startAnimation(it) }
            viewModel.onFavoriteButtonClick()
        }

        viewModel.observeFavoriteState().observe(viewLifecycleOwner) {
            renderLikeButton(it)
        }

        initToolbar()
    }

    private fun initToolbar() {
        binding?.vacancyToolbar?.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun renderLikeButton(isFavorite: Boolean) {
        val imageResource = if (isFavorite) R.drawable.favorites_on
        else R.drawable.favorites_off
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
                viewModel.isFavorite()
                binding?.progressBarForLoad?.isVisible = false
                binding?.placeholderServerError?.isVisible = false
            }

            is VacancyDetailsScreenState.Error -> {
                showError()
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
                .load(vacancy.employer?.logoUrls?.smallLogo)
                .placeholder(R.drawable.employer_logo_placeholder)
                .centerCrop().transform(
                    RoundedCorners(
                        this@VacancyDetailsFragment.resources.getDimensionPixelSize(
                            R.dimen.corner_radius_12
                        )
                    )
                )
                .into(employerLogo)

            companyName.text = vacancy.employer?.name
            experience.text = vacancy.experience?.name
            scheduleEmployment.text = vacancy.schedule?.name
            description.setText(
                HtmlCompat.fromHtml(
                    vacancy.description?.addSpacesAfterLiTags() ?: "",
                    FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM
                )
            )
            keySkills.setText(
                HtmlCompat.fromHtml(
                    getKeySkillsText(vacancy.keySkills),
                    FROM_HTML_MODE_COMPACT
                )
            )
            contactsName.text = vacancy.contacts?.name
            contactsEmail.text = vacancy.contacts?.email
            contactsPhone.text = getPhonesText(vacancy.contacts?.phones)
            contactsComment.text = getPhonesCommentsText(vacancy.contacts?.phones)

            hideEmptyViews(vacancy)
        }
    }

    private fun showLoading() {
        binding?.progressBarForLoad?.isVisible = true
        binding?.placeholderServerError?.isVisible = false
    }

    private fun showError() {
        binding?.progressBarForLoad?.isVisible = false
        binding?.placeholderServerError?.isVisible = true
    }

    private fun String.addSpacesAfterLiTags(): String {
        return this.replace(Regex("<li>\\s<p>|<li>"), "<li>\u00A0")
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
        var text = "<ul>"
        keySkills?.forEach { text += "<li>\u00A0 ${it.name}</li>\n" }
        text += "</ul>"
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
            contactsCommentGroup.isVisible =
                !vacancy?.contacts?.phones?.firstOrNull()?.comment.isNullOrBlank()
            contactsPersonGroup.isVisible = !vacancy?.contacts?.name.isNullOrBlank()
            keySkillsGroup.isVisible = !vacancy?.keySkills.isNullOrEmpty()
        }
    }
}