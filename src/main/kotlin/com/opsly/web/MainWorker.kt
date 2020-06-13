package com.opsly.web

import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*
import kotlin.system.*

class MainWorker {
    companion object {

        private val client = OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()

        private fun log(): Logger = LoggerFactory.getLogger("MainWorker")

        fun run(): String {

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

//                while (count < 3 && !urlstatus) {
//                    println("Count is $count")
//                    println("urlstatus is $urlstatus")
//
//                    client.newCall(urlrequest).enqueue(object : Callback {
//                        override fun onFailure(call: Call, e: IOException) {
//                            e.printStackTrace()
//                        }
//
//                        override fun onResponse(call: Call, urlresponse: Response) {
//                            urlresponse.use {
//                                if (!urlresponse.isSuccessful) {
//                                    throw IOException("Unexpected code $urlresponse")
//                                } else {
//                                    println("In the else section")
//                                    println("Status was $urlstatus")
//
//                                    urlstatus = true
//                                    println("Status in now $urlstatus")
//                                    println(urlresponse.body()!!.string())
//                                    log().info("**** $url ************")
////                                    output = output + urlresponse.body()!!.string()
////                                    println("OUTPUT String is $output ")
//                                }
//
////                                println(urlresponse.body()!!.string())
////                                log().info("**** $url ************")
//                            }
//                        }
//                    })
//
//                    count++
//                }

                client.newCall(urlrequest).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, urlresponse: Response) {
                        urlresponse.use {
                            if (!urlresponse.isSuccessful) throw IOException("Unexpected code $urlresponse")

                            var responsestring = urlresponse.body()!!.string()
                            println(responsestring)
                            output += responsestring
                            println("Output string is $output")
                        }
                    }
                })

                println("URL is $url")
            }

            println("End of program - Output is $output")
            return output

        }
    }
}