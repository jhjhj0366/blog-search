package com.jhj.blogsearch.config.feign;

import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverFeignConfig {

    @Bean
    public RequestInterceptor naverRequestInterceptor(
            @Value("${api.naver.client.id}") String NAVER_CLIENT_ID,
            @Value("${api.naver.client.secret}") String NAVER_CLIENT_SECRET) {

        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", NAVER_CLIENT_ID);
            requestTemplate.header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
        };
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(
                5, TimeUnit.SECONDS,
                10, TimeUnit.SECONDS,
                true
        );
    }
}