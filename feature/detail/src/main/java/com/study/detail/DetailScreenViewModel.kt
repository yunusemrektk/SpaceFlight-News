package com.study.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(

) : ViewModel() {
    val detailScreenUiState = MutableStateFlow<DetailScreenUIState>(DetailScreenUIState.Loading)

    init {
        initValues()
    }

    fun initValues(){
        viewModelScope.launch {
            detailScreenUiState.value = DetailScreenUIState.Detail(provideNewsDetail(), errorMessage = "")
            delay(2000)
            detailScreenUiState.value = DetailScreenUIState.Detail(provideNewsDetail(), errorMessage = "asdasda")
            delay(2000)
        }
    }
    private fun provideNewsDetail(): NewsDetail {

        val box_title = "Heavyweight boxing legend George Foreman dies aged 76"
        val box_summary = "Boxing heavyweight legend George Foreman has died aged 76.\n" +
                "\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 wins including 68 knockouts, almost double that of Ali.\n" +
                "\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 wins including 68 knockouts, almost double that of Ali.\n" +
                "\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 wins including 68 knockouts, almost double that of Ali.\n" +
                "\n" +

                "Foreman retired in 1997 but not before he agreed to put his name to a best-selling grill - a decision that went on to bring him fortunes that dwarfed his boxing earnings."

        val box_date = "1 hour ago"
        val image = Icons.Default.Check

        return NewsDetail(box_title, box_summary, box_date, image)
    }

}