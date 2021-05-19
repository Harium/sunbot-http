package com.harium.suneidesis.http;

import com.harium.marine.Web;
import com.harium.suneidesis.chat.box.BaseChatBox;
import com.harium.suneidesis.chat.output.Output;

public class SunbotHttp extends BaseChatBox {

    private int port = 11883;

    private final MessageModule module;

    public SunbotHttp() {
        module = new MessageModule();
        this.parsers = module.getParsers();
        this.interceptors = module.getInterceptors();
    }

    public void init() {
        Web.port(port);
        Web.register(module);
        Web.init();
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
        module.setOutput(output);
    }

    @Override
    public Output getOutput() {
        return module.getOutput();
    }

}
