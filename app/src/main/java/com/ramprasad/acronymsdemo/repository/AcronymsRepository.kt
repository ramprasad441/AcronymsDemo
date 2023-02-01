package com.ramprasad.acronymsdemo.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.ramprasad.acronymsdemo.model.Abbreviations
import com.ramprasad.acronymsdemo.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ramprasad on 7/31/22.
 */
@Singleton
class AcronymsRepository @Inject constructor(
    private val retrofitClient: RetrofitClient
) {

    fun getAcronymMeaningsList(acronym: String): MutableLiveData<List<Abbreviations>> {
        val listData = MutableLiveData<List<Abbreviations>>()
        retrofitClient.getMeaningList(acronym)
            .enqueue(object : Callback<List<Abbreviations>> {
                override fun onResponse(
                    call: Call<List<Abbreviations>>,
                    response: Response<List<Abbreviations>>
                ) {
                    if (response.isSuccessful) {
                        listData.value = response.body()
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<List<Abbreviations>>, t: Throwable) {
                    listData.value = null
                    t.printStackTrace()
                }
            })
        return listData
    }
}
