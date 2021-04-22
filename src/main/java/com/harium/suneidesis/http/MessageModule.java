package com.harium.suneidesis.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harium.marine.model.WebModule;
import com.harium.suneidesis.chat.Interceptor;
import com.harium.suneidesis.chat.Parser;
import com.harium.suneidesis.chat.input.InputContext;
import com.harium.suneidesis.chat.output.Output;
import com.harium.suneidesis.chat.output.OutputContext;
import com.harium.suneidesis.chat.output.TextOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static spark.Spark.post;

public class MessageModule implements WebModule {

    public static final String PARAM_MESSAGE = "message";

    protected List<Parser> parsers = new ArrayList<>();
    protected List<Interceptor> interceptors = new ArrayList<>();

    protected Output output = new TextOutput();

    private ObjectMapper objectMapper = new ObjectMapper();

    public void init() {
        post("/", (request, response) -> {
            String json = request.body();
            JsonNode node = objectMapper.readTree(json);

            String message = "";
            Map<String, String> map = new HashMap<>();

            for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (PARAM_MESSAGE.equals(entry.getKey())) {
                    message = entry.getValue().textValue();
                } else {
                    map.put(entry.getKey(), entry.getValue().textValue());
                }
            }

            OutputWrapper outputWrapper = new OutputWrapper(output);
            onReceiveMessage(message, map, outputWrapper);

            return outputWrapper.getSentence();
        });
    }

    private void onReceiveMessage(String message, Map<String, String> params, Output output) {
        InputContext context = new InputContext();
        context.setSentence(message);
        context.getProperties().putAll(params);

        for (Interceptor interceptor : interceptors) {
            interceptor.intercept(context, output);
        }
        for (Parser parser : parsers) {
            if (parser.parse(context, output)) {
                break;
            }
        }
    }

    class OutputWrapper implements Output {

        private String sentence;

        private Output output;

        public OutputWrapper(Output output) {
            this.output = output;
        }

        @Override
        public void print(String sentence, OutputContext context) {
            this.sentence = sentence;
            output.print(sentence, context);
        }

        @Override
        public void produceFile(String path, String description) {
            output.produceFile(path, description);
        }

        public String getSentence() {
            return sentence;
        }
    }
}
