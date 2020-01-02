package com.jung.channel.api.config.channel;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

public interface ChannelResponseHandler {

    Object convertResponse(ServerHttpRequest request, ServerHttpResponse response, Object body);

}
