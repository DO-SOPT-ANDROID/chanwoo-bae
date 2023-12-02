package org.sopt.dosopttemplate.network // ktlint-disable filename

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.sopt.dosopttemplate.BuildConfig
import org.sopt.dosopttemplate.network.doandroid.ReqresService
import retrofit2.Retrofit

object reqresApiFactory {
    private const val BASE_URL = BuildConfig.USER_BASE_URL

    private val json = Json { ignoreUnknownKeys = true }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)

    object ServicePool {
        val reqresService = reqresApiFactory.create<ReqresService>()
    }
}
