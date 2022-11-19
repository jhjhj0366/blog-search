package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.client.dto.KakaoDTO;
import java.util.List;
import java.util.stream.Collectors;

public class KakaoSearchMapper implements SearchMapper<KakaoDTO.Res> {

    @Override
    public SearchDTO.Res mapper(KakaoDTO.Res res) {
        return SearchDTO.Res.builder()
                            .totalCount(res.getMeta().getTotalCount())
                            .pageableCount(res.getMeta().getPageableCount())
                            .apiName(res.isFallBack() ? "NAVER-BLOG" : "KAKAO-BLOG")
                            .isEnd(res.getMeta().isEnd())
                            .documents(getDocuments(res))
                            .build();
    }

    private List<SearchDTO.Res.Document> getDocuments(KakaoDTO.Res res) {
        return res.getDocuments().stream().map(doc -> SearchDTO.Res.Document.builder()
                            .title(doc.getTitle())
                            .contents(doc.getContents())
                            .url(doc.getUrl())
                            .blogName(doc.getBlogName())
                            .thumbNail(doc.getThumbNail())
                            .dateTime(doc.getDateTime()).build()).collect(Collectors.toList());
    }
}