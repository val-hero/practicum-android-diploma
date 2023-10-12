package ru.practicum.android.diploma.vacancy_details.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.Resource
import ru.practicum.android.diploma.favorites.data.entity.FavoriteVacancyEntity
import ru.practicum.android.diploma.favorites.data.entity.toDomain
import ru.practicum.android.diploma.favorites.domain.usecase.AddToFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.DeleteFromFavorites
import ru.practicum.android.diploma.favorites.domain.usecase.GetFromFavorite
import ru.practicum.android.diploma.favorites.domain.usecase.IsInFavoritesCheck
import ru.practicum.android.diploma.search.domain.models.VacancyDetails
import ru.practicum.android.diploma.search.domain.usecase.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.vacancy_details.ui.state.VacancyDetailsScreenState

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

    private val _stateVacancyInfoDb = MutableLiveData<VacancyDetails?>()
    val stateVacancyInfoDb: LiveData<VacancyDetails?> = _stateVacancyInfoDb


    fun observeFavoriteState(): LiveData<Boolean> = isFavoriteLiveData

    fun isFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            vacancy.let {
                isInFavoritesCheckUseCase(it.id)
                    .collect {
                        isFavorite = it
                        isFavoriteLiveData.postValue(isFavorite)
                    }
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
                        _uiState.postValue(VacancyDetailsScreenState.Content(response.data))
                        vacancy = response.data
                    }

                    is Resource.Error -> {
                        _uiState.postValue(VacancyDetailsScreenState.Error(response.errorType))
                    }
                }
            }
        }
    }

    fun getVacancyFromDb(vacancy: FavoriteVacancyEntity) {
        viewModelScope.launch {
            getFromFavoriteUseCase(vacancy.id).collect { vacancyFromDb ->

                renderStateVacancyInfoDb(vacancyFromDb)
            }
        }
    }

    private fun renderStateVacancyInfoDb(vacancyFromDb: FavoriteVacancyEntity) {
        if (vacancyFromDb == null)
            _stateVacancyInfoDb.postValue(null)
        else
            _stateVacancyInfoDb.postValue(vacancyFromDb.toDomain())
    }

}