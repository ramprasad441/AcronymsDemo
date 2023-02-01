package com.ramprasad.acronymsdemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ramprasad on 1/29/23.
 */
data class Abbreviations(
    @SerializedName("sf")
    val abbreviation: String,
    @SerializedName("lfs")
    val longForms: List<LongForm>
)
