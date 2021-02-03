package examples;

import com.harium.suneidesis.chat.box.ChatBox;
import com.harium.suneidesis.chat.box.EchoBox;
import com.harium.suneidesis.http.HTTPBox;

/**
 * Simulate client
 * curl -XPOST -d '{"message":"Hello World!"}' localhost:11883
 */
public class BasicExample {

  public static void main(String[] args) {
    ChatBox bot = new EchoBox();

    HTTPBox http = new HTTPBox();
    http.port(11883);
    http.addBox(bot);
    http.init();
  }

}
