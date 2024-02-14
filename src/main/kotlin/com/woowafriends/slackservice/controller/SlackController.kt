package com.woowafriends.slackservice.controller

import com.woowafriends.slackservice.service.SlackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/slack")
class SlackController(
        @Autowired var slackService: SlackService
) {

    @GetMapping("/holiday/check")
    fun sendChannelHolidayCheck() {
        slackService.sendChannelHolidayCheck()
    }

    @GetMapping("/dm/send/me")
    fun dmSendMe() {
        slackService.dmSendMe()
    }
}

