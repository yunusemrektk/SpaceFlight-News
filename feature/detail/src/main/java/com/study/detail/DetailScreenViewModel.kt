package com.study.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.data.repository.OfflineUserDataRepository
import com.study.detail.navigation.ITEM_ID
import com.study.domain.GetDetailUseCase
import com.study.domain.SaveFavoriteUseCase
import com.study.model.NewsDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    val getDetailUseCase: GetDetailUseCase,
    val saveFavoriteUseCase: SaveFavoriteUseCase,
    val offlineUserDataRepository: OfflineUserDataRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val detailScreenUiState = MutableStateFlow<DetailScreenUIState>(DetailScreenUIState.Loading)
    val itemId = savedStateHandle[ITEM_ID] ?: 0

    init {
        initValues()
    }

    fun initValues(){
        viewModelScope.launch {
            Log.d("DetailScreenViewModel", "Get Detail Item Id: ${itemId}")
            getDetailUseCase.invoke(itemId)
                .catch {
                    Log.e("DetailScreenViewModel", "Get Detail Error: ${it.message}")
                    detailScreenUiState.value = DetailScreenUIState.Detail(errorMessage = "Can not get the details of the news")
                }
                .collect { item ->
                    //TODO IS LIKED?
                    detailScreenUiState.value = DetailScreenUIState.Detail(item)
                }
        }
    }

    fun onFavoriteClicked(newsDetail: NewsDetail) {
        //TODO IS ITEM LIKED BEFORE
        viewModelScope.launch {
            saveFavoriteUseCase.invoke(newsDetail, true)
                .catch {
                    Log.e("DetailScreenViewModel", "Save Favorite Error: ${it.message}")
                }
                .collect {
                    Log.i("DetailScreenViewModel", "Save Favorite Success}")
                }
        }
    }
}