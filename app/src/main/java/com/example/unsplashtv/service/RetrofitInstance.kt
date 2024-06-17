package com.example.unsplashtv.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object CustomTrustManager : X509TrustManager {
    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()

    fun getSSLSocketFactory(): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(this)
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        return sslContext.socketFactory
    }
}

val okHttpClient = OkHttpClient.Builder()
    .sslSocketFactory(CustomTrustManager.getSSLSocketFactory(), CustomTrustManager)
    .hostnameVerifier(HostnameVerifier { _, _ -> true })
    .build()

object RetrofitInstance {
    val api : UnsplashApiService by lazy {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApiService::class.java)
    }
}