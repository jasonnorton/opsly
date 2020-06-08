package com.opsly.web

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args){
        setBannerMode(Banner.Mode.OFF)
    }
}
