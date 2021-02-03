# sunbot-http
Plugin to turn your Suneidesis Chatbot into a HTTP server

## How to use it

```java
    ChatBox bot = new EchoBox(); // Use your own box

    HTTPBox http = new HTTPBox();
    http.port(11883);
    http.addBox(bot);
    http.init();
```