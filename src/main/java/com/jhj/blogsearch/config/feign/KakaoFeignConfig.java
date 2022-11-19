package com.jhj.blogsearch.config.feign;

import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig {

    @Bean
    public RequestInterceptor kakaoRequestInterceptor(@Value("${api.kakao.token}") String KAKAO_API_KEY) {
        return requestTemplate -> requestTemplate.header("Authorization", KAKAO_API_KEY);
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(
                5, TimeUnit.SECONDS,
                15, TimeUnit.SECONDS,
                true
        );
    }
}