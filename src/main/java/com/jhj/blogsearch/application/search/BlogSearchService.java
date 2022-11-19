package com.jhj.blogsearch.application.search;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.client.KakaoFeignClient;
import com.jhj.blogsearch.application.search.client.dto.KakaoDTO;
import com.jhj.blogsearch.application.search.model.SearchPage;
import com.jhj.blogsearch.application.search.model.mapper.KakaoSearchMapper;
import com.jhj.blogsearch.application.search.model.mapper.SearchMapper;
import com.jhj.blogsearch.application.trend.TrendKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService {

    private final KakaoFeignClient kakaoFeignClient;
    private final TrendKeywordService trendKeywordService;

    public SearchPage searchBlog(SearchDTO.Req request) {

        final SearchDTO.Res searchResDTO = searchKakaoBlog(request);
        trendKeywordService.updateCountByKeyword(request.getQuery());

        return SearchPage.builder()
                        .apiName(searchResDTO.getApiName())
                        .apiSort(String.valueOf(request.getSort()))
                        .isEnd(searchResDTO.isEnd())
                        .content(searchResDTO.getDocuments())
                        .pageNumber(searchResDTO.getPageableCount())
                        .pageSize(request.getPageSize())
                        .total(searchResDTO.getTotalCount())
                        .build();
            }

    SearchDTO.Res searchKakaoBlog(SearchDTO.Req request) {
        KakaoDTO.Res res = kakaoFeignClient.getBlogResult(request);
        SearchMapper<KakaoDTO.Res> searchMapper = new KakaoSearchMapper();
        return searchMapper.mapper(res);
    }
}