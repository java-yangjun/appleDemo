package com.jung.channel.api.config.channel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.*;

@Component
public final class ChannelConfigurationCollection {

    private final Map<String, List<HandlerInterceptor>> interceptorRegistries = new HashMap<>();
    private final Map<String, ChannelExceptionHandler> exceptionHandlers = new HashMap<>();
    private final Map<String, ChannelResponseHandler> responseHandlers = new HashMap<>();

    public ChannelConfigurationCollection(List<ChannelConfiguration> channelConfigurations) {
        Set<String> channels = new HashSet<>();
        for (ChannelConfiguration channelConfiguration : channelConfigurations) {
            if (channelConfiguration instanceof PlaceholderChannelConfiguration) {
                continue;
            }
            String path = channelConfiguration.getPath();
            if (StringUtils.isBlank(path)) {
                throw new RuntimeException("渠道配置路径不能为空");
            }
            if (channels.contains(path)) {
                throw new RuntimeException("已注册过该渠道配置：" + path);
            }
            channels.add(path);

            List<HandlerInterceptor> interceptorRegistry = new ArrayList<>();
            channelConfiguration.addInterceptors(interceptorRegistry);
            interceptorRegistries.put(path, interceptorRegistry);

            ChannelResponseHandler responseHandler = channelConfiguration.getResponseHandler();
            if (responseHandler != null) {
                responseHandlers.put(path, responseHandler);
            }

            ChannelExceptionHandler exceptionHandler = channelConfiguration.getExceptionHandler();
            if (exceptionHandler != null) {
                exceptionHandlers.put(path, exceptionHandler);
            }
        }
    }

    public void mergeInterceptors(InterceptorRegistry registry) {
        for (Map.Entry<String, List<HandlerInterceptor>> entry : interceptorRegistries.entrySet()) {
            String path = entry.getKey() + "**";
            for (HandlerInterceptor interceptor : entry.getValue()) {
                registry.addInterceptor(interceptor).addPathPatterns(path);
            }
        }
    }

    public ChannelResponseHandler getResponseHandler(String path) {
        for (Map.Entry<String, ChannelResponseHandler> entry : responseHandlers.entrySet()) {
            if (StringUtils.startsWith(path, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public ChannelExceptionHandler getExceptionHandler(String path) {
        for (Map.Entry<String, ChannelExceptionHandler> entry : exceptionHandlers.entrySet()) {
            if (StringUtils.startsWith(path, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Component
    static final class PlaceholderChannelConfiguration implements ChannelConfiguration {

        @Override
        public String getPath() {
            return null;
        }

    }

}
