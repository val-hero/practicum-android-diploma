package ru.practicum.android.diploma.favorites.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.favorites.domain.usecase.DeleteFromFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.GetFavorites
import ru.practicum.android.diploma.favorites.ui.state.FavoritesScreenState
import ru.practicum.android.diploma.search.domain.models.VacancyDetails

class FavoritesFragmentViewModel(
    private val getFavoritesUseCase: GetFavorites,
    private val deleteFromFavoritesUseCase: DeleteFromFavorites,
) : ViewModel() {

    private val contentStateLiveData = MutableLiveData<FavoritesScreenState>()

    fun observeContentState(): LiveData<FavoritesScreenState> = contentStateLiveData

    var isClickable = true
    private var _selectedVacancyIdLiveData = MutableLiveData<String>()
    val selectedVacancyIdLiveData: LiveData<String> = _selectedVacancyIdLiveData

    private val vacancyClickDebounce =
        debounce<Boolean>(Constants.CLICK_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isClickable = it
        }

    init {
        getFavoritesVacancies()
    }

    fun getFavoritesVacancies() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { favoritesVacancies ->
                processResult(favoritesVacancies)
            }
        }
    }

    private fun processResult(vacanciesList: List<VacancyDetails>) {
        when {
            vacanciesList.isEmpty() -> {
                contentStateLiveData.value = FavoritesScreenState.Empty
            }

            else -> {
                contentStateLiveData.value = FavoritesScreenState.FavoritesVacancies(vacanciesList)
            }
        }
    }

    fun onVacancyClick() {
        isClickable = false
        vacancyClickDebounce(true)
    }

    fun onLongVacancyClick(vacancyId: String) {
        isClickable = false
        vacancyClickDebounce(true)
        _selectedVacancyIdLiveData.value = vacancyId
    }

    fun deleteVacancy() {
        viewModelScope.launch(Dispatchers.IO) {
            val vacancyId = selectedVacancyIdLiveData.value
            if (vacancyId != null) {
                deleteFromFavoritesUseCase(vacancyId)
                getFavoritesVacancies()
            }
        }
    }
}


