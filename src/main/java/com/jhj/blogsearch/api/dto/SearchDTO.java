package com.jhj.blogsearch.api.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.jhj.blogsearch.application.search.model.SortType;

public class SearchDTO {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(staticName = "of")
    public static class Req {
        @NotNull
        private String query;
        @Builder.Default
        private SortType sort = SortType.ACCURACY;
        @Min(1)
        private int pageNumber;
        @Min(1)
        private int pageSize;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor(staticName = "of")
    public static class Res {
        private final int totalCount;
        private final int pageableCount;
        private final String apiName;
        private final boolean isEnd;
        private final List<Document> documents;

        @Getter
        @Builder
        public static class Document {
            private String title;
            private String contents;
            private String url;
            private String blogName;
            private String thumbNail;
            private String dateTime;
        }
    }
}