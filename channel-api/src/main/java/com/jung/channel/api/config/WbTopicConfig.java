package com.jung.channel.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "channel.wb.topic")
@Data
@Component
public class WbTopicConfig {

    /**
     * 更新58分消息
     */
    private String updateWbScoreMsg;
}
