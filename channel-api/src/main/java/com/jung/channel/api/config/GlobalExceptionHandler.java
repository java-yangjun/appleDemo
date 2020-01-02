package com.jung.channel.api.config;

import com.jung.channel.api.base.vo.BaseResponse;
import com.jung.channel.api.config.channel.ChannelConfigurationCollection;
import com.jung.channel.api.config.channel.ChannelExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ChannelConfigurationCollection channelConfigurationCollection;

    public GlobalExceptionHandler(ChannelConfigurationCollection channelConfigurationCollection) {
        this.channelConfigurationCollection = channelConfigurationCollection;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Object defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        String path = request.getServletPath();
        ChannelExceptionHandler exceptionHandler = channelConfigurationCollection.getExceptionHandler(path);
        if (exceptionHandler != null) {
            return exceptionHandler.resolveException(request, response, throwable);
        } else {
            BaseResponse<Void> baseResponse = new BaseResponse<>();
            baseResponse.setCode(-1);
            baseResponse.setMsg(throwable.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }
    }

}
