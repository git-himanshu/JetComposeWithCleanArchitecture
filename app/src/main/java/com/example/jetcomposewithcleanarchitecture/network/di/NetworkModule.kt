package com.example.jetcomposewithcleanarchitecture.network.di

import android.util.Log
import com.example.jetcomposewithcleanarchitecture.network.BASE_URL
import com.example.jetcomposewithcleanarchitecture.network.IBikeNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(LoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideDevicesApi(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IBikeNetworkService {
        return retrofit.create(IBikeNetworkService::class.java)
    }
}


internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        Log.d("BikeNetwork:NetworkRequest",
            String.format(
                "Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()
            )
        )
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        Log.d("BikeNetwork:NetworkRequest",
            String.format(
                "Received response for %s in %.1fms%n%s \n %s",
                response.request().url(), (t2 - t1) / 1e6, response.headers(),response.body().toString()
            )
        )
        return response
    }
}