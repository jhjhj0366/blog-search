package com.jhj.blogsearch.application.search.client.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SearchDTOTransfer {

    public KakaoDTO.Res naverResToKakaoRes(NaverDTO.Res res) {
        KakaoDTO.Res.Meta meta = KakaoDTO.Res.Meta.builder()
                                .totalCount(res.getTotal())
                                .pageableCount(res.getDisplay())
                                .isEnd(false)
                                .build();

        List<KakaoDTO.Res.Document> documents = getKakaoDocuments(res);
        return KakaoDTO.Res.of(meta, documents, true);
    }

    private List<KakaoDTO.Res.Document> getKakaoDocuments(NaverDTO.Res res) {
        return res.getItems().stream().map(item -> KakaoDTO.Res.Document.builder()
                                                    .title(item.getTitle())
                                                    .contents(item.getDescription())
                                                    .url(item.getUrl())
                                                    .blogName(item.getBloggerName())
                                                    .thumbNail(item.getLink())
                                                    .dateTime(item.getPostDate()).build()).collect(Collectors.toList());
    }
}