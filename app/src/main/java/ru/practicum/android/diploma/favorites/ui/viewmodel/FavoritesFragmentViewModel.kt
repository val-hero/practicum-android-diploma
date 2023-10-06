package ru.practicum.android.diploma.favorites.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Constants
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.favorites.domain.usecase.GetFavorites
import ru.practicum.android.diploma.favorites.ui.state.FavoritesScreenState
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoritesFragmentViewModel(
    private val getFavoritesuseCase: GetFavorites
) : ViewModel() {

    private val contentStateLiveData = MutableLiveData<FavoritesScreenState>()
    fun observeContentState(): LiveData<FavoritesScreenState> = contentStateLiveData

    var isClickable = true

    private val vacancyClickDebounce =
        debounce<Boolean>(Constants.CLICK_DEBOUNCE_DELAY_MILLIS, viewModelScope, false) {
            isClickable = it
        }

    init {
        getFavoritesVacancies()
    }

    fun getFavoritesVacancies() {
        viewModelScope.launch {
            getFavoritesuseCase
                .invoke()
                .collect { favoritesVacancies ->
                    processResult(favoritesVacancies)
                }
        }
    }

    private fun processResult(vacanciesList: List<Vacancy>) {
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

}


