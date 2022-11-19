package com.jhj.blogsearch.application.search.client.dto;

import java.util.List;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class NaverDTO {

    @Getter
    public static class Res {
        private String lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<Item> items;

        @Getter
        public static class Item {
            private String title;
            private String link;
            private String url;
            private String description;
            @JsonProperty("bloggername")
            private String bloggerName;
            @JsonProperty("bloggerlink")
            private String bloggerLink;
            @JsonProperty("postdate")
            private String postDate;
        }
    }
}