package com.woowafriends.slackservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

@Configuration
class SlackConfig {

    @Value("\${slack.token-woowa}")
    lateinit var slackToken: String


    @Bean(name = ["slackHttpHeaders"])
    fun slackHttpHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $slackToken")
        headers.add("Content-type", "application/json; charset=utf-8")
        return headers
    }

}
