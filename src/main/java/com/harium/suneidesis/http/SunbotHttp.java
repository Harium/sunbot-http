package com.harium.suneidesis.http;

import com.harium.marine.Web;
import com.harium.suneidesis.chat.Parser;
import com.harium.suneidesis.chat.box.BaseChatBox;
import com.harium.suneidesis.chat.output.Output;

public class SunbotHttp extends BaseChatBox {

  private int port = 11883;

  private MessageModule module = new MessageModule();

  public void init() {
    Web.port(port);
    Web.register(module);
    Web.init();
  }

  @Override
  public void addParser(Parser parser) {
    module.addParser(parser);
  }

  public SunbotHttp output(Output output) {
    module.output = output;
    return this;
  }

  public SunbotHttp port(int port) {
    this.port = port;
    return this;
  }

  @Override
  public void sendMessage(String channel, String message) {
    module.sendMessage(channel, message);
  }

  @Override
  public void setOutput(Output output) {
    output(output);
  }

  @Override public Output getOutput() {
    return module.output;
  }

}
