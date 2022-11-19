package com.jhj.blogsearch.application.trend;

import com.jhj.blogsearch.infra.entity.TrendKeyword;
import com.jhj.blogsearch.infra.repository.TrendKeywordRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TrendKeywordRepositoryTest {

    @Autowired
    private TrendKeywordRepository trendKeywordRepository;

    @Test
    void shouldGetTop10KeywordOrderbyCount() {

        // given
        TrendKeyword keyword = new TrendKeyword("hello", 1L, LocalDateTime.now());
        TrendKeyword keyword1 = new TrendKeyword("hello-kakao", 4L, LocalDateTime.now());
        TrendKeyword keyword2 = new TrendKeyword("hello-moji", 10L, LocalDateTime.now());
        List<TrendKeyword> trendKeywordList = new ArrayList<>();

        trendKeywordList.add(keyword);
        trendKeywordList.add(keyword1);
        trendKeywordList.add(keyword2);

        trendKeywordRepository.saveAll(trendKeywordList);

        // when
        List<TrendKeyword> resultList = trendKeywordRepository.findTop10TrendKeywordByOrderByCountDesc();

        // then
        Assertions.assertEquals(resultList.get(0).getKeyword(), "hello-moji");
    }

}