package com.ramprasad.acronymsdemo.network

import com.ramprasad.acronymsdemo.model.Abbreviations
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ramprasad on 1/29/23.
 */
interface RetrofitClient {

    @GET("software/acromine/dictionary.py")
    fun getMeaningList(@Query("sf") acronym: String): Call<List<Abbreviations>>
}
