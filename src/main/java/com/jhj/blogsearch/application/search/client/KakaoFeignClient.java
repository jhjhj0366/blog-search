package com.jhj.blogsearch.application.search.client;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.model.SortType;
import com.jhj.blogsearch.application.search.client.dto.KakaoDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverDTO;
import com.jhj.blogsearch.application.search.client.dto.SearchDTOTransfer;
import com.jhj.blogsearch.config.feign.KakaoFeignConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ref ) https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
 */

@FeignClient(name = "kakaoClient", url = "https://dapi.kakao.com", configuration = KakaoFeignConfig.class, fallbackFactory = KakaoFeignClient.KaKaoClientFallbackFactory.class)
public interface KakaoFeignClient {

    @GetMapping(path = "/v2/search/blog", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    KakaoDTO.Res getBlogResult(@SpringQueryMap SearchDTO.Req request);

    @Slf4j
    @RequiredArgsConstructor
    @Component
    class KaKaoClientFallbackFactory implements FallbackFactory<KakaoFeignClient> {

        private final NaverFeignClient naverFeignClient;
        private final SearchDTOTransfer transfer;

        @Override
        public KakaoFeignClient create(Throwable cause) {
            return request -> {

                log.warn("Fallback : KakaoFeignClient.getBlogResult() called", cause);

                SortType naverSortType = SortType.getNaverSortType(String.valueOf(request.getSort()));
                NaverDTO.Res res = naverFeignClient.getBlogResult(SearchDTO.Req.of(request.getQuery(), naverSortType, request.getPageNumber(), request.getPageSize()));

                return transfer.naverResToKakaoRes(res);
            };
        }
    }
}