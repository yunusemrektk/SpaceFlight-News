package com.study.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : ViewModel() {
    val mainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)

    init {
        initValues()
    }

    private fun initValues(){
        viewModelScope.launch {
            mainScreenUiState.value = MainScreenUiState.Summary
        }
    }
}