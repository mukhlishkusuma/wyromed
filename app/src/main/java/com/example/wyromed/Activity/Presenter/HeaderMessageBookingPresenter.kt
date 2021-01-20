package com.example.wyromed.Activity.Presenter

import com.example.wyromed.Activity.Interface.HeaderMessageBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.HeaderMessageBooking.ResponseHeaderMessageBooking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap


class HeaderMessageBookingPresenter(val headerMessageBookingInterface: HeaderMessageBookingInterface) {
    fun getHeaderMessageBooking(
        tokenType: String?,
        token: String?,
        id: Int
    ){
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        val url: String = "bookingorder/" + id

        // Header
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"
        map["Accept-Encoding"] = "gzip, deflate, br"


        NetworkConfig.service()
            .getHeaderMessageBooking(url, map)
            .enqueue(object : Callback<ResponseHeaderMessageBooking> {

                override fun onFailure(call: Call<ResponseHeaderMessageBooking>, t: Throwable) {
                    headerMessageBookingInterface.onErrorHeaderMessageBooking(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseHeaderMessageBooking>,
                    response: Response<ResponseHeaderMessageBooking>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        headerMessageBookingInterface.onSuccessHeaderMessageBooking(message, data)
                    } else {
                        headerMessageBookingInterface.onErrorHeaderMessageBooking(error)
                    }
                }
            })
    }
}