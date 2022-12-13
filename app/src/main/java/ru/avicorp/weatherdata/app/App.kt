package ru.avicorp.weatherdata.app

import android.app.Application
import android.widget.Toast
import com.squareup.picasso.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
private const val TOKEN = ""
private const val BASE_URL = "https://openweathermap.org/data/2.5/"
private const val OKHTTP_CONNECT_TIMEOUT = 15L
private const val OKHTTP_WRITE_TIMEOUT = 25L
private const val OKHTTP_READ_TIMEOUT = 25L


class App :Application() {
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        retrofit = createRetrofit()
    }


    private fun createRetrofit(): Retrofit {
        if (TOKEN.isBlank()) Toast.makeText(this@App,"Token is blank, go to https://openweathermap.org ...",Toast.LENGTH_LONG).show()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)//неизменяемая часть
            .addConverterFactory(GsonConverterFactory.create())// создаем конвертер
            .client(createHttpClient())
            .build()
    }


    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->//перехватчик (запросов.ответов)
                chain.proceed(chain
                    .request().run {
                        val url = url()
                            .newBuilder()
                            .addQueryParameter("appid", TOKEN)//добавляем токен
                            .build()

                        newBuilder()
                            .url(url)
                            .build()
                    }
                )
            }
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .apply {

                // логгирование
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(this)
                    }
                }
            }
            .build()
    }
}