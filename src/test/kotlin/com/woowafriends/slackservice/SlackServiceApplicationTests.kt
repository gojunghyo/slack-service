package com.woowafriends.slackservice

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class SlackServiceApplicationTests {

    @Test
    fun contextLoads() {
        val str: String =  """
               123  (목) +  ~  + 456 +  (수)
               ggg
               hhh
           """.trimIndent()
        println(str)

        Assertions.assertThat(1).isEqualTo(1)
    }

}
