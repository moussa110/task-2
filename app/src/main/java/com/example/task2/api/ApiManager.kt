package com.example.task2.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {

    private fun isConnected(): Boolean {
//         var isconnect = false
//         val connectivityManager =
//             context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//         val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//         isconnect = activeNetwork?.isConnectedOrConnecting == true
        return true;
    }

    private var retrofit: Retrofit? = null;
    private fun getInstance(): Retrofit {
        if (retrofit == null) {

//             val cacheSize = 10 * 1024 * 1024 // 10 MB
//             val cache = Cache(context.cacheDir, cacheSize.toLong())


            val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.e("api", message)
                }
            })
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //.addNetworkInterceptor(OnlineInterceptor())
                    //.addInterceptor(OfflineInterceptor(isConnected()))
                    //.cache(cache)
                    .build()

            return Retrofit.Builder()
                .baseUrl("https://api.github.com/users/square/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        } else
            return retrofit!!;
    }

    public fun getWebServices(): WebServices {
        return getInstance().create(WebServices::class.java);
    }


    private class OnlineInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            val maxAge = 60 // read from cache for 60 seconds even if there is internet connection

            return response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
    }

    private class OfflineInterceptor(var isconnect: Boolean) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            if (!isconnect) {
                val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            return chain.proceed(request)
        }


    }
}


