package com.study.favorite

import com.study.domain.RemoveFavoriteUseCase
import com.study.favorite.FavoriteScreenUiState.ListView
import com.study.favorite.FavoriteScreenUiState.Loading
import com.study.model.NewsDetail
import com.study.testing.TestOfflineUserDataRepository
import com.study.testing.util.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FavoriteScreenViewModelTest {

    private val offlineUserDataRepository = TestOfflineUserDataRepository()
    private val removeFavoriteUseCase = RemoveFavoriteUseCase(offlineUserDataRepository)
    private lateinit var viewModel: FavoriteScreenViewModel

    private val testDispatcher = StandardTestDispatcher()
    @get:Rule
    val dispatcherRule = MainDispatcherRule()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Before
    fun setup() {
        viewModel = FavoriteScreenViewModel(
            removeFavoriteUseCase = removeFavoriteUseCase,
            offlineUserDataRepository = offlineUserDataRepository
            )
    }

    @Test
    fun initialStateIsLoading() {
        assertEquals(Loading, viewModel.favoriteScreenUiState.value)
    }

    @Test
    fun saveFavorites() = runTest {
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.favoriteScreenUiState.collect{
                if (it is ListView){
                    assertEquals(1, it.likedNews.size)
                }
            }
        }
        offlineUserDataRepository.saveDetails(exampleDetail)
        offlineUserDataRepository.saveFavorites(exampleDetail, true)
        advanceUntilIdle()

        collectJob.cancel()
    }

    @Test
    fun removeFavorites() = runTest {
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher()) {
            offlineUserDataRepository.saveDetails(exampleDetail)
            offlineUserDataRepository.saveFavorites(exampleDetail, true)
            offlineUserDataRepository.saveFavorites(exampleDetail, false)
            viewModel.favoriteScreenUiState.collect{
                if (it is ListView){
                    assertEquals(0, it.likedNews.size)
                }
            }
        }

        advanceUntilIdle()

        collectJob.cancel()
    }

}


private val exampleDetail = NewsDetail(
    id = 30183,
    title = "Investigaciones de la NASA en la estación espacial ayudan a impulsar la ciencia lunar",
    article = "La Estación Espacial Internacional sustenta una amplia gama de actividades científicas, desde la observación de nuestro universo hasta el logro de avances en investigaciones médicas, y es un campo de pruebas activo en la tecnología para futuras misiones de exploración en la Luna y más allá. La misión Blue Ghost 1 de Firefly Aerospace aterrizó en […]",
    date = "2025-03-25T16:34:43Z",
    image = "https://www.nasa.gov/wp-content/uploads/2025/03/nicer-lexi.jpg",
)