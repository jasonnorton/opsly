package com.opsly.web

import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*

class Main {

    companion object {

        internal val client = OkHttpClient().newBuilder()
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

            for (url in urls) {
                val urlrequest = Request.Builder()
                        .url(url)
                        .build()

                var count = 0
                var urlstatus = false
//                output = ""

                val responseone  = async {
                    val aaaa = ""
                    doSomethingUsefulOne(urlrequest, aaaa)
                }

//                println("The answer is ${responseone.await() + two.await() + three.await()}")

                println("URL is $url")
            }

            println("End of program - Output is $output").toString()
//            delay(1000L)

//            return output

        }

//        fun xxxx(urlrequest: Request) = runBlocking<Unit> {
//            val time = measureTimeMillis {
//                val one = async { doSomethingUsefulOne() }
//                val two = async { doSomethingUsefulTwo() }
//                val three = async { doSomethingUsefulThree() }
//                println("The answer is ${one.await() + two.await() + three.await()}")
//            }
//            println("Completed in $time ms")
//        }

        suspend fun doSomethingUsefulOne(urlrequest: Request, responsestring: String): String {

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
            return responsestring
//            return 13
        }

        suspend fun doSomethingUsefulTwo(): Int {
            delay(1000L) // pretend we are doing something useful here, too
            return 29
        }

        suspend fun doSomethingUsefulThree(): Int {
            delay(1000L) // pretend we are doing something useful here, too
            return 29
        }


    }
}