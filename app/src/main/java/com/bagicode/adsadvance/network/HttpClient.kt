package com.bagicode.adsadvance.network

import android.util.Log
import com.bagicode.bagicodebaseutils.utils.Helpers
import com.readystatesoftware.chuck.ChuckInterceptor
import com.bagicode.adsadvance.BagicodeTravel
import com.bagicode.adsadvance.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client : Retrofit?=null
    private var endpoint : Endpoint?=null

    init {
        buildRetrofitClient()
    }

//    Agar tidak berulang membuat Endpoint
    fun getApi() : Endpoint? {
        if (endpoint == null) {
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = BagicodeTravel.getApp().getToken()
        buildRetrofitClient(token)
    }


    fun buildRetrofitClient(token : String?) {

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
//            untuk memudahkan menghandle api
            Log.v("chuc", "ini data 1 "+BagicodeTravel.getApp())
            builder.addInterceptor(ChuckInterceptor(BagicodeTravel.getApp()))
            Log.v("chuc", "ini data 2 "+ChuckInterceptor(BagicodeTravel.getApp()).toString())
        }

        if (token != null) {
            builder.addInterceptor(getInterceptorWithHeader("Authorization", "${token}"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL+"bcadvance/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        endpoint = null
    }

    private fun getInterceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getInterceptorWithHeader(header)
    }

    private fun getInterceptorWithHeader(headers : Map<String, String>) : Interceptor {
        return Interceptor {
            val original = it.request()
            val builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method(), original.body())
            it.proceed(builder.build())
        }
    }

    companion object {
        private val M_INSTANCE : HttpClient = HttpClient()
        @Synchronized
        fun getInstance() : HttpClient {
            return M_INSTANCE
        }
    }
}