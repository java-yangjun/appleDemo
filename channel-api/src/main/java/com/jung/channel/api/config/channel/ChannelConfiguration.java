package com.jung.channel.api.config.channel;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * 子渠道配置
 */
public interface ChannelConfiguration {

    /**
     * 获取子渠道路径
     * 结尾需要有 "/" 来进行域分割
     * 子渠道首页必须配置为 "/api/channel-name/"
     */
    String getPath();

    /**
     * 注册渠道拦截器
     */
    default void addInterceptors(List<HandlerInterceptor> interceptors) {}

    /**
     * 获取相应处理器
     */
    default ChannelResponseHandler getResponseHandler() {
        return null;
    }

    /**
     * 获取异常处理器
     */
    default ChannelExceptionHandler getExceptionHandler() {
        return null;
    }

}
