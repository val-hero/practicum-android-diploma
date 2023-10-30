package ru.practicum.android.diploma.vacancy_details.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.favorites.domain.usecase.AddToFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.DeleteFromFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.GetFromFavorite
import ru.practicum.android.diploma.favorites.domain.usecase.IsInFavoritesCheck
import ru.practicum.android.diploma.search.domain.models.VacancyDetails
import ru.practicum.android.diploma.search.domain.usecase.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.vacancy_details.ui.state.VacancyDetailsScreenState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class VacancyDetailsViewModel(
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase,
    private val addToFavoritesUseCase: AddToFavorites,
    private val deleteFromFavoritesUseCase: DeleteFromFavorites,
    private val isInFavoritesCheckUseCase: IsInFavoritesCheck,
    private val getFromFavoriteUseCase: GetFromFavorite
) : ViewModel() {

    private val _uiState = MutableLiveData<VacancyDetailsScreenState>()
    val uiState: LiveData<VacancyDetailsScreenState> = _uiState
    private val isFavoriteLiveData = MutableLiveData<Boolean>()
    private var isFavorite: Boolean = false
    private lateinit var vacancy: VacancyDetails
    private val stateVacancyInfoDb = MutableLiveData<VacancyDetails?>()
    private val _isActiveButtonSameVacancies = MutableLiveData<Boolean>()
    var isActiveButtonSameVacancies: LiveData<Boolean> = _isActiveButtonSameVacancies

    fun observeFavoriteState(): LiveData<Boolean> = isFavoriteLiveData

    suspend fun isFavorite(id: String): Boolean = suspendCoroutine { continuation ->
        viewModelScope.launch(Dispatchers.IO) {
            isInFavoritesCheckUseCase(id)
                .collect {
                    val isFavorite = it
                    isFavoriteLiveData.postValue(isFavorite)
                    continuation.resume(isFavorite)
                }
        }
    }

    fun onFavoriteButtonClick() {
        isFavorite = !isFavorite
        isFavoriteLiveData.value = isFavorite

        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite) {
                addToFavoritesUseCase(vacancy)
            } else {
                deleteFromFavoritesUseCase(vacancy.id)
            }
        }
    }

    fun fetchDetails(id: String) {
        _uiState.value = VacancyDetailsScreenState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            getVacancyDetailsUseCase(id).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        isFavorite(id)
                        _isActiveButtonSameVacancies.postValue(true)
                        _uiState.postValue(VacancyDetailsScreenState.Content(response.data))
                        vacancy = response.data
                    }

                    is Resource.Error -> {
                        if (isFavorite(id)) {
                            getVacancyFromDb(id)
                            _isActiveButtonSameVacancies.postValue(false)
                        } else
                            _uiState.postValue(VacancyDetailsScreenState.Error(response.errorType))
                    }
                }
            }
        }
    }

    private fun getVacancyFromDb(id: String) {

        viewModelScope.launch {
            getFromFavoriteUseCase(id).collect { vacancyFromDb ->
                renderStateVacancyInfoDb(vacancyFromDb)
            }
        }
    }

    private fun renderStateVacancyInfoDb(vacancyFromDb: VacancyDetails) {
        vacancy = vacancyFromDb
        stateVacancyInfoDb.postValue(vacancyFromDb)
        _uiState.postValue(VacancyDetailsScreenState.Content(vacancy))
    }

}