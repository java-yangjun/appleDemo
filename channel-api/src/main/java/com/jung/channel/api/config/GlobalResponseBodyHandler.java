package com.jung.channel.api.config;

import com.alibaba.fastjson.JSON;
import com.jung.channel.api.config.channel.ChannelConfigurationCollection;
import com.jung.channel.api.config.channel.ChannelResponseHandler;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    private final ChannelConfigurationCollection channelConfigurationCollection;

    public GlobalResponseBodyHandler(ChannelConfigurationCollection channelConfigurationCollection) {
        this.channelConfigurationCollection = channelConfigurationCollection;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String path = request.getURI().getPath();
        ChannelResponseHandler responseHandler = channelConfigurationCollection.getResponseHandler(path);
        if (responseHandler != null) {
            Object newBody = responseHandler.convertResponse(request, response, body);
            if (body instanceof String) {
                newBody = JSON.toJSONString(newBody);
            }
            return newBody;
        } else {
            return body;
        }
    }

}
