package com.jhj.blogsearch.application.search.client;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverDTO;
import com.jhj.blogsearch.config.feign.NaverFeignConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ref ) https://developers.naver.com/docs/serviceapi/search/blog/blog.md#%EB%B8%94%EB%A1%9C%EA%B7%B8
 */

@FeignClient(name = "naverClient", url = "https://openapi.naver.com", configuration = NaverFeignConfig.class, fallbackFactory = NaverFeignClient.NaverClientFallbackFactory.class)
public interface NaverFeignClient {

    @GetMapping(path = "/v1/search/blog", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    NaverDTO.Res getBlogResult(@SpringQueryMap SearchDTO.Req request);

    @Slf4j
    @Component
    class NaverClientFallbackFactory implements FallbackFactory<NaverFeignClient> {

        @Override
        public NaverFeignClient create(Throwable cause) {
            return req -> {
                log.warn("Fallback : NaverFeignClient.getBlogResult() called", cause);
                return null;
            };
        }
    }
}