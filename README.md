# slack-service


## 기술 스택
```
프레임워크: Spring Boot 3.1.8
언어: Kotlin (JAVA 17)
빌드도구: Gradle 8.5
```

## 개발환경 구성

```
1. java 17 (Build Tools -> Gradle -> 17 셋팅)
2. file -> Project Settings -> Project Java 17 (corretto-17) Setting
3. gradle build
```

## 슬랙 관련 참고 링크
```
슬랙 API 참고 링크 https://api.slack.com/methods/users.profile.get
슬랙 가입하여 유저/봇 토큰 발급한후 사용이 가능합니다. 
```


## Description
```
1. 슬랙 Token 으로 인증 한후
2. API 를 통하여 User 의 SlackId 값을 받아옵니다.
3. 해당 유저에게만 DM 을 발송 할수있습니다.
4. 추가로 업무채널에 발송하여 매주 진행 하던 휴가자 조사를 자동화 할수있습니다. 
```
