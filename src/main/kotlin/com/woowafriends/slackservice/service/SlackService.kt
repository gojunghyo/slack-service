package com.woowafriends.slackservice.service

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.configurationprocessor.json.JSONException
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.client.RestTemplate
import java.time.DayOfWeek
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class SlackService(
       @Autowired private val slackHttpHeaders: HttpHeaders
) {
    private val userList: List<String> = listOf("mz_gojgho@woowafriends.com")

    private val POST_URL: String = "https://slack.com/api/chat.postMessage"
    private val LOOKUP_URL: String = "https://slack.com/api/users.lookupByEmail"
    private val ITO_POOL_CHANEL_ID = ""


    fun dmSendMe() {
        userList.forEach { email ->
            println(email)

            val calendar = getCalendar()
            val slackId = getSlackIdByEmail(email)
            println("slackId = $slackId")

            val jsonObject = JSONObject()
            jsonObject.put("channel", slackId)
//            jsonObject.put("text", "<@"+email.split("@")[0]+"> 님, 안녕하세요 !")
            jsonObject.put("text", "$calendar 휴가자 스레드")

            val requestEntity = HttpEntity(jsonObject.toString(),
            slackHttpHeaders)

            postRequest(POST_URL, requestEntity)
        }
    }

    fun getSlackIdByEmail(email: String) : String {
        val url = LOOKUP_URL + "?email=" + email

        val requestEntity = HttpEntity<String>(slackHttpHeaders)

        val restTemplate = RestTemplate()
        val responseEntity: ResponseEntity<String> = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String::class.java
        )

        val jsonObject = JSONObject(responseEntity.body)
        println("jsonObject = $jsonObject")
        val id: String = jsonObject.getJSONObject("user").get("id") as String
        println("id = $id")
        return id
    }

    fun sendMZHolidayCheck() {
        try {
            val calendar = getCalendar()

            if(StringUtils.hasText(calendar)) {
                val jsonObject = JSONObject()
                jsonObject.put("channel", ITO_POOL_CHANEL_ID)
                jsonObject.put("text", "$calendar 휴가자 스레드")
                val requestEntity : HttpEntity<String> = HttpEntity(jsonObject.toString(),
                        slackHttpHeaders)
                postRequest(POST_URL, requestEntity)
            }
        } catch (e: JSONException) {
            throw IllegalStateException(e.message)
        }

    }

    fun postRequest(url:String, requestEntity: HttpEntity<String>) {
        val responseEntity: ResponseEntity<String> =  RestTemplate().exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String::class.java
        )

        val statusCode = responseEntity.statusCode
        println("response status = $statusCode")
    }


    fun getCalendar(): String {
        val dayOfWeek = LocalDate.now().dayOfWeek
        var holidayStartDate: LocalDate?
        val holidayEndDate: LocalDate?

        when (dayOfWeek) {
            DayOfWeek.MONDAY -> {
                holidayStartDate = LocalDate.now().minusDays(4);
                holidayEndDate = holidayStartDate.plusDays(6)
            }
            DayOfWeek.TUESDAY -> {
                holidayStartDate = LocalDate.now().minusDays(5);
                holidayEndDate = holidayStartDate.plusDays(6)
            }
            DayOfWeek.WEDNESDAY -> {
                holidayStartDate = LocalDate.now().minusDays(6);
                holidayEndDate = holidayStartDate.plusDays(6)
            }
            DayOfWeek.THURSDAY -> {
                holidayStartDate = LocalDate.now().minusDays(7);
                holidayEndDate = holidayStartDate.plusDays(6)
            }
            DayOfWeek.FRIDAY -> {
                holidayStartDate = LocalDate.now().minusDays(8);
                holidayEndDate = holidayStartDate.plusDays(6)
            }
            else -> throw IllegalStateException("휴가자 스레드 생성일이 아닙니다.")
        }

        if (holidayStartDate.dayOfWeek == DayOfWeek.THURSDAY &&
                holidayEndDate.dayOfWeek == DayOfWeek.WEDNESDAY) {
            return """
               $holidayStartDate  (목)  ~  $holidayEndDate  (수)
           """.trimIndent()
        }
        return ""
    }
}
