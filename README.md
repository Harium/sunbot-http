# sunbot-http
Plugin to turn your Suneidesis Chatbot into a HTTP server

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis/sunbot-http/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis/sunbot-http/)

## How to use it

```java
    ChatBox bot = new EchoBox(); // Use your own box

    HTTPBox http = new HTTPBox();
    http.port(11883);
    http.addBox(bot);
    http.init();
```

## Sending messages

You can use any tool to post messages to the bot:

```shell script
curl -XPOST -d '{"message":"Hello World!"}' localhost:11883
```