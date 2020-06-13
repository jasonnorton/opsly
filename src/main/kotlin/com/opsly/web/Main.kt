package com.opsly.web

import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*

class Main {

    companion object {

        private val client = OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()

        private fun log(): Logger = LoggerFactory.getLogger("MainWorker")

        fun run(): String = runBlocking<String> {

            var output: String = ""

            val urls = listOf(
                    "https://takehome.io/facebook",
                    "https://takehome.io/twitter",
                    "https://takehome.io/instagram")

            var count = 0
            for (url in urls) {
                println("URL is $url")
                count = count +1
                val urlrequest = Request.Builder()
                        .url(url)
                        .build()

                val responseone  = async {
                    doSomethingUsefulOne(urlrequest)
                }

//                println("The answer is ${responseone.await() + two.await() + three.await()}")

            }

            return@runBlocking "bob"
//            println("End of program - Output is '$output'").toString()
//            return output

        }

        suspend fun doSomethingUsefulOne(urlrequest: Request): String {

            client.newCall(urlrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, urlresponse: Response) {
                    urlresponse.use {
                        if (!urlresponse.isSuccessful) throw IOException("Unexpected code $urlresponse")

                        var responsestring = urlresponse.body()!!.string()
                        println(responsestring)
//                        output += responsestring
//                        println("Output string is $output")
                    }
                }
            })
            return "test"
//            return 13
        }

        suspend fun doSomethingUsefulTwo(): String {
            delay(1000L) // pretend we are doing something useful here, too
            return "Two"
        }
//
//        suspend fun doSomethingUsefulThree(): Int {
//            delay(1000L) // pretend we are doing something useful here, too
//            return 29
//        }
//
//
    }
}