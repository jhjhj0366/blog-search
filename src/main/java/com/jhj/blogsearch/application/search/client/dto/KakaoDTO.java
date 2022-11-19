package com.jhj.blogsearch.application.search.client.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@Getter
public class KakaoDTO {

    @Getter
    @Builder
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private Meta meta;
        private List<Document> documents;
        @Builder.Default
        private boolean isFallBack = false;

        @Getter
        @Builder
        @AllArgsConstructor(staticName = "of")
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Meta {
            @JsonProperty("total_count")
            private int totalCount;
            @JsonProperty("pageable_count")
            private int pageableCount;
            @JsonProperty("is_end")
            private boolean isEnd;
        }

        @Getter
        @Builder
        @AllArgsConstructor(staticName = "of")
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Document {
            private String title;
            private String contents;
            private String url;
            @JsonProperty("blogname")
            private String blogName;
            @JsonProperty("thumbnail")
            private String thumbNail;
            @JsonProperty("datetime")
            private String dateTime;
        }
    }
}
