package com.jhj.blogsearch.application.trend;

import static java.util.stream.Collectors.toList;

import com.jhj.blogsearch.api.dto.TrendKeywordDTO;
import com.jhj.blogsearch.infra.entity.TrendKeyword;
import com.jhj.blogsearch.infra.repository.RedisTrendKeywordRepository;
import com.jhj.blogsearch.infra.repository.TrendKeywordRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrendKeywordService {

    private final RedisTrendKeywordRepository redisTrendKeywordRepository;
    private final TrendKeywordRepository trendKeywordRepository;

    public void updateCountByKeyword(String keyword) {
        redisTrendKeywordRepository.updateCountByKeyword(keyword);
    }

    public List<TrendKeywordDTO> getTop10TrendKeywordsLookAside() {
        try {
            return redisTrendKeywordRepository.findTop10ByOrderByCountDesc();
        } catch (Exception e) {
            log.error("Failed, get the redis cache trend keyword, {}", e.getMessage());
        }
        return trendKeywordRepository.findTop10TrendKeywordByOrderByCountDesc().stream()
                .map(t -> TrendKeywordDTO.of(t.getKeyword(), t.getCount())).collect(toList());
    }

    @Transactional
    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void backupTrendKeyword() {
        List<TrendKeywordDTO> redisTop10Keywords = redisTrendKeywordRepository.findTop10ByOrderByCountDesc();
        List<TrendKeyword> list = redisTop10Keywords.stream()
                .map(t -> TrendKeyword.of(t.getKeyword(), t.getCount()))
                .collect(toList());

        log.info("Back up  redis data in database : " + LocalDateTime.now());
        trendKeywordRepository.saveAll(list);
    }
}
