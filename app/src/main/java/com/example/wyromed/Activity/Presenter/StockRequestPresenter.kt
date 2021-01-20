package com.example.wyromed.Activity.Presenter

import com.example.wyromed.Activity.Interface.StockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.ResponseGetStockRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.HashMap

class StockRequestPresenter(val stockRequestInterface: StockRequestInterface) {
    fun getAllStockRequest(tokenType: String?, token: String?){
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()

        // Header
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"
        map["Content-Type"] = "application/json"
        map["Accept-Encoding"] = "gzip, deflate, br"

        NetworkConfig.service()
            .getAllStockRequest(map)
            .enqueue(object : Callback<ResponseGetStockRequest> {

                override fun onFailure(call: Call<ResponseGetStockRequest>, t: Throwable) {
                    stockRequestInterface.onErrorGetStockRequest(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseGetStockRequest>, response: Response<ResponseGetStockRequest>) {
                    val body = response.body()
                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        stockRequestInterface.onSuccessGetStockRequest(message, data)

                    } else {
                        stockRequestInterface.onErrorGetStockRequest(message)
                    }
                }
            })
    }
}