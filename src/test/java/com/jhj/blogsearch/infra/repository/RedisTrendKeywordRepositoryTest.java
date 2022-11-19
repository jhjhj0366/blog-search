package com.jhj.blogsearch.infra.repository;

import static java.util.stream.Collectors.toList;

import com.jhj.blogsearch.api.dto.TrendKeywordDTO;
import com.jhj.blogsearch.config.LocalRedisConfig;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

@DataRedisTest
class RedisTrendKeywordRepositoryTest {

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;


    @BeforeEach
    void setUp() {
        zSetOperations.incrementScore("keyword", "hello-kakao-bank", 1d);
    }

    @AfterEach
    void setDown() {
        zSetOperations.remove("keyword", "hello-kakao-bank");
    }

    @Test
    void shouldupdateKeywordCount() {

        // given
        String keyword = "hello-kakao-bank";

        // when
        Double count = zSetOperations.incrementScore("keyword", keyword, 1d);

        // then
        Assertions.assertEquals(count, 2d);
    }


    @Test
    void shouldfindTop10Rank() {
        String redisKey = "keyword";
        // give
        zSetOperations.add(redisKey, "hello-kakao", 5d);
        zSetOperations.add(redisKey, "hello-world", 4d);
        zSetOperations.add(redisKey, "hello", 2d);

        // when
        Set<TypedTuple<String>> tupleSet = zSetOperations.reverseRangeWithScores(redisKey, 0, 9);
        List<TrendKeywordDTO> list = Objects.requireNonNull(tupleSet).stream()
                .map(tuple -> TrendKeywordDTO.of(tuple.getValue(),
                        Objects.requireNonNull(tuple.getScore()).longValue()))
                .collect(toList());

        // then
        Assertions.assertEquals(list.get(0).getCount(), 5);

    }

}