package com.opsly.web

import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainWorker {
    companion object {

        private val client = OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .build()

        private fun log(): Logger = LoggerFactory.getLogger("MainWorker")

        fun run(): String {

            val facebookrequest = Request.Builder()
                    .url("https://takehome.io/facebook")
                    .build()

            val twitterrequest = Request.Builder()
                    .url("https://takehome.io/twitter")
                    .build()

            val instagramrequest = Request.Builder()
                    .url("https://takehome.io/instagram")
                    .build()

            client.newCall(facebookrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, fbresponse: Response) {
                    fbresponse.use {
                        if (!fbresponse.isSuccessful) throw IOException("Unexpected code $fbresponse")

                        println(fbresponse.body()!!.string())
                        log().info("**** FACEBOOK ************")
                    }
                }
            })

            client.newCall(twitterrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, twresponse: Response) {
                    twresponse.use {
                        if (!twresponse.isSuccessful) throw IOException("Unexpected code $twresponse")

                        println(twresponse.body()!!.string())
                        log().info("**** TWITTER ************")
                    }
                }
            })

            client.newCall(instagramrequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, inresponse: Response) {
                    inresponse.use {
                        if (!inresponse.isSuccessful) throw IOException("Unexpected code $inresponse")

                        println(inresponse.body()!!.string())
                        log().info("**** INSTAGRAM ************")
                    }
                }
            })

            return "test"

        }
    }
}