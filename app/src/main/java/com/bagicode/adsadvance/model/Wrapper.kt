package com.bagicode.adsadvance.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @Expose //jika tidak ada datanya tidak akan error
    @SerializedName("codeMessage")
    var codeMessage: String?,
    @SerializedName("codeStatus")
    var codeStatus: String?,
    @SerializedName("data")
    var data: T? = null
)