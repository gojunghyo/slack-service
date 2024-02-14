package com.woowafriends.slackservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlackServiceApplication

fun main(args: Array<String>) {
    runApplication<SlackServiceApplication>(*args)
}
