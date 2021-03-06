package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.InboxInterface
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Inbox.ResponseInbox
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import com.example.wyromed.Response.Stock.ResponseStock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class InboxPresenter(val inboxInterface: InboxInterface) {
    fun getAllInbox(context: Context){
        var connection = false
        // Query Param Map
//        val queryMap: MutableMap<String, String> = HashMap()
//        queryMap["search"] = ""

        NetworkConfig.service(context)
            .getAllInbox()
            .enqueue(object : Callback<ResponseInbox> {

                override fun onFailure(call: Call<ResponseInbox>, t: Throwable) {
                    for (retries in 0..2){
                        getAllInbox(context)
                        if (connection == false){
                            continue
                        }
                        break
                    }
                    inboxInterface.onErrorGetInbox(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseInbox>, response: Response<ResponseInbox>) {
                    connection = true
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        inboxInterface.onSuccessGetInbox(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        inboxInterface.onErrorGetInbox(message)
                    }
                }
            })
    }
}