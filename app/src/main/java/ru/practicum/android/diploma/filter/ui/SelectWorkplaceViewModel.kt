package ru.practicum.android.diploma.filter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.usecase.GetFilterSettingsUseCase

class SelectWorkplaceViewModel(private val getFilterSettingsUseCase: GetFilterSettingsUseCase): ViewModel() {

    private val _filterSettings = MutableLiveData<FilterParameters?>()
    fun updateFilterSettings(): LiveData<FilterParameters?> = _filterSettings
    fun getFilterSettings() {
        viewModelScope.launch {
            _filterSettings.postValue(getFilterSettingsUseCase())
        }
    }

}