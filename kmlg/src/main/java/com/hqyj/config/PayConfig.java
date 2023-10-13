package com.hqyj.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:payConfig.properties")
public class PayConfig {
    @Value("${gatewayUrl}")
    private String gatewayUrl;
    @Value("${appId}")
    private String appId;
    @Value("${privateKey}")
    private String privateKey;
    @Value("${publicKey}")
    private String publicKey;
    @Value("${signType}")
    private String signType;
    @Value("${charset}")
    private String charset;
    @Value("${format}")
    private String format;
    @Value("${returnUrl}")
    private String returnUrl;
    @Value("${notifyUrl}")
    private String notifyUrl;
}
