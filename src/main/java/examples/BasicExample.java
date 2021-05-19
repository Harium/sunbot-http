package examples;

import com.harium.suneidesis.chat.Parser;
import com.harium.suneidesis.chat.box.EchoBox;
import com.harium.suneidesis.http.SunbotHttp;

/**
 * Simulate client
 * curl -XPOST -d '{"message":"Hello World!"}' localhost:11883
 * curl -XPOST -d '{"message":"Hello World!", "lang":"en"}' localhost:11883
 */
public class BasicExample {

  public static void main(String[] args) {
    Parser bot = new EchoBox();

    SunbotHttp http = new SunbotHttp();
    http.port(11883);
    http.addParser(bot);
    http.init();
  }

}
