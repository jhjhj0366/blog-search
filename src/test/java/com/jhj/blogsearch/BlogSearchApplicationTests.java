package com.jhj.blogsearch;

import com.jhj.blogsearch.config.LocalRedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

@Import(LocalRedisConfig.class)
@EnableCaching
@SpringBootTest
class BlogSearchApplicationTests {

    @Test
    void contextLoads() {
    }

}
