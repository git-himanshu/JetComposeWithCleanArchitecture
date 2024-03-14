package com.example.core.network.networkInterceptor

import android.util.Log
import com.example.core.network.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        Log.d(
                Constants.LOG_TAG,
                String.format(
                        Constants.REQUEST_LOG_PLACE_HOLDER,
                        request.url(), chain.connection(), request.headers()
                )
        )
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        Log.d(
                Constants.LOG_TAG,
                String.format(
                        Constants.RESPONSE_LOG_PLACE_HOLDER,
                        response.request().url(),
                        (t2 - t1) / 1e6,
                        response.headers(),
                        response.body().toString()
                )
        )
        return response
    }
}