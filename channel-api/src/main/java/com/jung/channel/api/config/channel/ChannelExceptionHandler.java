package com.jung.channel.api.config.channel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ChannelExceptionHandler {

    Object resolveException(HttpServletRequest request, HttpServletResponse response, Throwable throwable);

}
