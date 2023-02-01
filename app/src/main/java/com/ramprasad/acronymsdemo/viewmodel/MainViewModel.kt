package com.ramprasad.acronymsdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramprasad.acronymsdemo.model.Abbreviations
import com.ramprasad.acronymsdemo.repository.AcronymsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ramprasad on 1/31/23.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val acronymsRepository: AcronymsRepository
) : ViewModel() {

    fun fetchAcronymsResponse(shortForm: String): MutableLiveData<List<Abbreviations>> {
        return acronymsRepository.getAcronymMeaningsList(shortForm)
    }
}
