package com.woowafriends.slackservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "slack-url")
class SlackPropertiesConfig {

    lateinit var post: String
    lateinit var lookup: String
    lateinit var channelId: String
}

