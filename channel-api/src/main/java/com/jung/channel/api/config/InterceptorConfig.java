package com.jung.channel.api.config;

import com.jung.channel.api.config.channel.ChannelConfigurationCollection;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;
    private final ChannelConfigurationCollection channelConfigurationCollection;

    public InterceptorConfig(TokenInterceptor tokenInterceptor, ChannelConfigurationCollection channelConfigurationCollection) {
        this.tokenInterceptor = tokenInterceptor;
        this.channelConfigurationCollection = channelConfigurationCollection;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**");
        // 添加渠道拦截器
        channelConfigurationCollection.mergeInterceptors(registry);
    }

}
