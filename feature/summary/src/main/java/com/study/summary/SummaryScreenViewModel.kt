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
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n\n" +
                "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
                "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
                "Foreman retired in 1997 but not before he agreed to put his name to a best-selling grill - a decision that went on to bring him fortunes that dwarfed his boxing earnings."

        val box_date = "1 hour ago"

        val delay_title = "After Delays, ESA to Publish Launcher Challenge Call Next Week"
        val delay_summary = "During a press briefing following the 332nd ESA Council meeting, ESA Director General Josef Aschbacher announced that the agency will publish a call for proposals for the European Launcher Challenge in the coming week.\n\n" +
                "Announced in November 2023, the European Launcher Challenge is intended to support the development of sovereign launch capabilities and, ultimately, a successor to Ariane 6. While few specifics have been confirmed, early indications suggest the programme will offer multiple awards of €150 million each.\n\n" +
                "During his annual press briefing in January 2025, ESA Director General Josef Aschbacher stated that the agency would publish a call for proposals, which the agency calls an Invitation to Tender, “around the February timeframe.” However, this tentative timeline wasn’t realized. The Director General has now said that the call will be published next week.\n\n" +
                "“On the European launcher challenge, we had quite an important debate on the future of launchers,” said Aschbacher. “The ITT, the Invitation to Tender, will go out next week and it will, of course, prepare the ground for the smaller launchers, microlaunchers and minilaunchers, to become part of this Challenge. And we do sincerely hope to see some of the first of these new launches being launched very soon.”"
        val delay_date = "12/12/2024"

        val new1 = NewsSummary(box_title, box_summary, box_date)
        val new2 = NewsSummary(delay_title, delay_summary, delay_date)

        listNews.add(new1)
        listNews.add(new2)
        listNews.add(new1)
        return listNews
    }



}