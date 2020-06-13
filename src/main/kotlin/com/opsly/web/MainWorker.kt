package com.opsly.web

import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*

class MainWoker {

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

            val facebookurl = Request.Builder()
                    .url("https://takehome.io/facebook")
                    .build()

            val twitterurl = Request.Builder()
                    .url("https://takehome.io/twitter")
                    .build()

            val instagramurl = Request.Builder()
                    .url("https://takehome.io/instagram")
                    .build()

            var count = 0


            val facebookresponseone  = async {
                doSomethingUsefulOne(facebookurl)
            }

            val twitterresponseone  = async {
                doSomethingUsefulOne(twitterurl)
            }

            val instagramresponseone  = async {
                doSomethingUsefulOne(instagramurl)
            }

            println("All calls have responded ${facebookresponseone.await() +
                    twitterresponseone.await() +
                    instagramresponseone.await()}")

            return@runBlocking "$facebookresponseone" +
                    "$twitterresponseone" +
                    "$instagramresponseone"

//            println("End of program - Output is '$output'").toString()
//            return output

        }

        suspend fun doSomethingUsefulOne(urlrequest: Request): String {

            var xxx = ""
            client.newCall(urlrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, urlresponse: Response) {
                    urlresponse.use {
                        if (!urlresponse.isSuccessful) throw IOException("Unexpected code $urlresponse")

                        var responsestring = urlresponse.body()!!.string()
                        println("URl response string is - $responsestring")
                        xxx = responsestring
                    }
                }
            })
            return xxx
        }

    }
}