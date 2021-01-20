package com.example.wyromed.Response.HeaderMessageBooking

import com.google.gson.annotations.SerializedName

class MetaHeaderMessageBooking: ResponseHeaderMessageBooking() {

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: Int? = null

    @field:SerializedName("message")
    val message: String? = null

    @field:SerializedName("error")
    val error: String? = ""
}