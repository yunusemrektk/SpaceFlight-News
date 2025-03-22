package com.study.summary

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryScreenViewModel @Inject constructor(

) : ViewModel()
{
    val summaryScreenUiState = MutableStateFlow(SummaryScreenUiState(isLoading = true))

    init {
        initValues()
    }

    fun initValues() {
        viewModelScope.launch {
            summaryScreenUiState.update {
                it.copy(isLoading = false, newsSummary = provideNewsItem())
            }
        }
    }

    private fun provideNewsItem(): MutableList<NewsSummary> {
        val listNews: MutableList<NewsSummary> = emptyList<NewsSummary>().toMutableList()
        val box_title = "Heavyweight boxing legend George Foreman dies aged 76"
        val box_summary = "Boxing heavyweight legend George Foreman has died aged 76.\n" +
                "\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
                "\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
                "\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
                "\n" +

                "Foreman retired in 1997 but not before he agreed to put his name to a best-selling grill - a decision that went on to bring him fortunes that dwarfed his boxing earnings."

        val box_date = "1 hour ago"
        val new1 = NewsSummary(box_title, box_summary, box_date)

        listNews.add(new1)
        listNews.add(new1)
        listNews.add(new1)
        return listNews
    }

}