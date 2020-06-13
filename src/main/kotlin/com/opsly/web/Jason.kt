package com.opsly.web

import kotlinx.coroutines.*
import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.system.*

class Jason {
    companion object {

        private val client = OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()

        var outputstring = ""

        private fun log(): Logger = LoggerFactory.getLogger("MainWorker")

        fun main() = runBlocking<String> {
            val time = measureTimeMillis {
                val one = async { doSomethingUsefulOne() }
                val two = async { doSomethingUsefulTwo() }
                val three = async { doSomethingUsefulThree() }
                println("All jobs complete ${one.await() + two.await() + three.await()}")
                outputstring = "$one+$two+$three"
            }
            println("All jobs completed in $time ms").toString()
            println("The completed string is - $outputstring")

            return@runBlocking com.opsly.web.Jason.Companion.outputstring
        }

        suspend fun doSomethingUsefulOne(): String {
            val urlrequest = geturlrequest("https://takehome.io/facebook")
            var responsestring = ""
            client.newCall(urlrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, urlresponse: Response) {
                    urlresponse.use {
                        if (!urlresponse.isSuccessful) throw IOException("Unexpected code $urlresponse")

                        responsestring = urlresponse.body()!!.string()
                        println("Facebook response is ${responsestring}")
                    }
                }
            })
            return responsestring
        }

        suspend fun doSomethingUsefulTwo(): String {
            val urlrequest = geturlrequest("https://takehome.io/twitter")
            var responsestring = ""
            client.newCall(urlrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, urlresponse: Response) {
                    urlresponse.use {
                        if (!urlresponse.isSuccessful) throw IOException("Unexpected code $urlresponse")

                        responsestring = urlresponse.body()!!.string()
                        println("Twitter response is ${responsestring}")
                    }
                }
            })
            return responsestring
        }

        suspend fun doSomethingUsefulThree(): String {
            val urlrequest = geturlrequest("https://takehome.io/instagram")
            var responsestring = ""
            client.newCall(urlrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, urlresponse: Response) {
                    urlresponse.use {
                        if (!urlresponse.isSuccessful) throw IOException("Unexpected code $urlresponse")

                        responsestring = urlresponse.body()!!.string()
                        println("Instagram response is $responsestring")
                    }
                }
            })
            return responsestring
        }

        fun geturlrequest(inputurl: String): Request {
            val urlrequest = Request.Builder()
                    .url(inputurl)
                    .build()
            return urlrequest
        }
    }
}