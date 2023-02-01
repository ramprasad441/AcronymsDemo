package com.ramprasad.acronymsdemo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ramprasad on 1/29/23.
 */
data class LongForm(
    @SerializedName("lf")
    val meaning: String,
    @SerializedName("vars")
    val variations: List<LongForm>
)
