# sunbot-http
Plugin to turn your Suneidesis Chatbot into a HTTP server

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis.sunbot/http/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis.sunbot/http/)

## How to use it

```java
    Parser bot = new EchoBox(); // Use your own box

    SunbotHttp http = new SunbotHttp();
    http.port(11883);
    http.addBox(bot);
    http.init();
```

## Sending messages

You can use the [Sunbot HTTP Client](https://github.com/Harium/sunbot-http-client) or any other HTTP tool:

### Curl

```shell script
curl -XPOST -d '{"message":"Hello World!"}' localhost:11883
```