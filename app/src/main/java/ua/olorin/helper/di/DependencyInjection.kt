package ua.olorin.helper.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.olorin.helper.DefaultViewModel
import ua.olorin.helper.network.WebService
import ua.olorin.helper.repository.Repository
import ua.olorin.helper.repository.RepositoryImpl

const val BASE_URL = "https://my-json-server.typicode.com/"

private fun createNetworkClient() : Retrofit {

    val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

}

private val retrofit: Retrofit = createNetworkClient()
private val webService = retrofit.create(WebService::class.java)

val appModule = module {
    single { webService }
    single<Repository> { RepositoryImpl(get()) }
    viewModel { DefaultViewModel(get()) }
}