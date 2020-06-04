package com.michaelcorral.mvptemplate.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.michaelcorral.mvptemplate.BuildConfig
import com.michaelcorral.mvptemplate.api.services.ItunesService
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT: Long = 20
private const val READ_TIMEOUT: Long = 20

val networkModule = module {
    single { provideItunesService(get()) }
    single { provideRetrofit(get(), get(), get()) }
    single { provideOkHttpClient() }
    single { provideKotlinxSerializationConverterFactory() }
    single { provideRxJava2CallAdapterFactory() }
}

private fun provideItunesService(retrofit: Retrofit): ItunesService =
    retrofit.create(ItunesService::class.java)

private fun provideRetrofit(
    client: OkHttpClient,
    kotlinxSerializationConverterFactory: Converter.Factory,
    adapterFactory: RxJava2CallAdapterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.itunes_base_api)
    .addConverterFactory(kotlinxSerializationConverterFactory)
    .addCallAdapterFactory(adapterFactory)
    .client(client)
    .build()

private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .build()

private fun provideKotlinxSerializationConverterFactory(): Converter.Factory = Json(
    JsonConfiguration(ignoreUnknownKeys = true, isLenient = true)
).asConverterFactory(provideContentType())

private fun provideContentType(): MediaType = MediaType.get("application/json")

private fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())