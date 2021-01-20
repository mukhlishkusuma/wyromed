package com.example.wyromed.Activity.Presenter

import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.SignInBody
import com.example.wyromed.Model.User
import com.example.wyromed.Response.Login.DataLogin
import com.example.wyromed.Activity.Interface.SignInInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInPresenter(val signInInterface: SignInInterface) {
    fun signin(signInBody:SignInBody){
        val map: MutableMap<String, String> = HashMap()
        map["Host"] = "absdigital.id"

        NetworkConfig.service()
            .signin(map, signInBody)
            .enqueue(object : Callback<DataLogin> {

                override fun onFailure(call: Call<DataLogin>, t: Throwable){
                    signInInterface.onErrorLogin(t.localizedMessage)
                }

                override fun onResponse(call: Call<DataLogin>, response : Response<DataLogin>){
                    val body = response.body()

                    if (response.isSuccessful){
                        signInInterface.onSuccessLogin(body?.data?.token_type, body?.data?.token, body?.meta?.message)
                    }
                    else {
                        signInInterface.onErrorLogin(body?.meta?.error)
                    }
                }
            })
    }
}