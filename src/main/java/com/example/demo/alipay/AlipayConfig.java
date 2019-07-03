//package com.example.demo.alipay;
//
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableConfigurationProperties(AlipayProperties.class)
//public class AlipayConfig {
//
//    private AlipayProperties properties;
//
//    public AlipayConfig(AlipayProperties properties) {
//        this.properties = properties;
//    }
//
//    @Bean
//    public AlipayTradeService alipayTradeService() {
//        return new AlipayTradeServiceImpl.ClientBuilder()
//                .setGatewayUrl(properties.getGatewayUrl())
//                .setAppid(properties.getAppid())
//                .setPrivateKey(properties.getAppPrivateKey())
//                .setAlipayPublicKey(properties.getAlipayPublicKey())
//                .setSignType(properties.getSignType())
//                .build();
//    }
//}