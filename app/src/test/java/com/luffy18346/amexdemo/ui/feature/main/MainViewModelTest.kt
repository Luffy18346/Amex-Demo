package com.luffy18346.amexdemo.ui.feature.main

import com.luffy18346.amexdemo.data.repository.FakePictureRepositoryImpl
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.domain.use_case.GetPicturesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    // Different kinds of Rules... @Get:Rule

    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepository: FakePictureRepositoryImpl
    private val testDispatcher = StandardTestDispatcher()

    //You can definitely use MockK instead of writing a manual FakePictureRepositoryImpl.
    // | Feature                  | **Fake (like `FakePictureRepositoryImpl`)**      | **MockK / Mocking**                     |
    //|--------------------------|--------------------------------------------|----------------------------------------|
    //| **Definition**           | A real implementation with test-controlled behavior | A dynamically created object with predefined responses |
    //| **Real Logic?**          | Yes, it may contain real or simplified logic | No, it's a stubbed interface/class      |
    //| **Maintenance**          | Needs manual code for logic                 | Less boilerplate, easier to set up     |
    //| **Use When**             | - Behavior depends on internal logic<br>- You want to simulate realistic usage | - You only care about verifying interactions or stubbing return values |
    //| **Best For**             | Integration or medium-level tests            | Unit tests, verifying calls/params     |

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakePictureRepositoryImpl()
        viewModel = MainViewModel(GetPicturesUseCase(fakeRepository), testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getPictures success updates viewState with correct data`() = runTest {
        fakeRepository.setPictures(samplePictures())

        viewModel.getPictures()
        advanceUntilIdle()

        val state = viewModel.viewState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isError)
        Assert.assertEquals(samplePictures(), state.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getPictures failure sets isError true`() = runTest {
        fakeRepository.shouldReturnError = true

        viewModel.getPictures()
        advanceUntilIdle()

        val state = viewModel.viewState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertTrue(state.isError)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Retry event triggers getPictures again`() = runTest {
        fakeRepository.setPictures(samplePictures())

        viewModel.setEvent(MainContract.Event.Retry)
        advanceUntilIdle()

        val state = viewModel.viewState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isError)
        Assert.assertEquals(samplePictures(), state.data)
    }

    private fun samplePictures() = listOf(
        Picture(
            format = "jpeg",
            width = 500,
            height = 1000,
            author = "author1",
            filename = "1.jpeg",
            id = 1,
            postUrl = "dummy_post_url_1",
            authorUrl = "dummy_author_url_1",
        ),
        Picture(
            format = "jpeg",
            width = 1000,
            height = 500,
            author = "author2",
            filename = "2.jpeg",
            id = 2,
            postUrl = "dummy_post_url_2",
            authorUrl = "dummy_author_url_2",
        ),
    )
}