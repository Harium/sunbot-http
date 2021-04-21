package com.harium.suneidesis.http;

import com.harium.marine.Web;
import com.harium.suneidesis.chat.Parser;
import com.harium.suneidesis.chat.box.BoxHandler;
import com.harium.suneidesis.chat.output.Output;

public class HTTPBox implements BoxHandler {

  private int port = 11883;

  private MessageModule module = new MessageModule();

  public void init() {
    Web.port(port);
    Web.register(module);
    Web.init();
  }

  @Override
  public void addParser(Parser parser) {
    module.parsers.add(parser);
  }

  public HTTPBox output(Output output) {
    module.output = output;
    return this;
  }

  public HTTPBox port(int port) {
    this.port = port;
    return this;
  }

  @Override
  public void sendMessage(String channel, String message) {

  }

}
