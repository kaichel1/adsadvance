package com.bagicode.adsadvance.network

import com.bagicode.adsadvance.model.Response.LoginResponse
import com.bagicode.adsadvance.model.Wrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Endpoint {
    @FormUrlEncoded // Untuk Handle Form Body Dari POSTMAN
    @POST("register.php") // Endpoint pada URL
    fun setRegister(
        @Field("email") email : String, // Field pada table/ key pada body di POSTMAN
        @Field("pass") pass : String,
        @Field("username") username : String
    ) : Observable<Wrapper<Any>>

    @FormUrlEncoded
    @POST("login.php")
    fun setLogin(
        @Field("email") email : String,
        @Field("pass") pass : String
    ) : Observable<Wrapper<LoginResponse>>
}