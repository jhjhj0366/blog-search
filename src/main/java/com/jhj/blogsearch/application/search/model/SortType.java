package com.jhj.blogsearch.application.search.model;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {

    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private final String kakaoSortType;
    private final String naverSortType;

    public static SortType getKakaoSortType(String type) {
        return Arrays.stream(SortType.values())
                .filter(s -> s.kakaoSortType.equals(type))
                .findFirst()
                .orElse(SortType.ACCURACY);
    }
    public static SortType getNaverSortType(String type) {
        return Arrays.stream(SortType.values())
                .filter(s -> s.naverSortType.equals(type))
                .findFirst()
                .orElse(SortType.ACCURACY);
    }
}