package com.ramprasad.acronymsdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.ramprasad.acronymsdemo.model.Abbreviations
import com.ramprasad.acronymsdemo.repository.AcronymsRepository
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ramprasad on 1/31/23.
 */
@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private val acronymsRepository = mock(AcronymsRepository::class.java)
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(acronymsRepository)
    }

    @Test
    fun testCaseForAcronymsList() {
        val liveData = mock<MutableLiveData<List<Abbreviations>>>()
        whenever(acronymsRepository.getAcronymMeaningsList(ArgumentMatchers.anyString())) doReturn liveData

        val result = mainViewModel.fetchAcronymsResponse("test")
        MatcherAssert.assertThat(result, IsEqual(liveData))
    }
}
