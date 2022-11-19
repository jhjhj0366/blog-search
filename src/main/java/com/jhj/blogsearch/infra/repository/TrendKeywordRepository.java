package com.jhj.blogsearch.infra.repository;

import com.jhj.blogsearch.infra.entity.TrendKeyword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendKeywordRepository extends JpaRepository<TrendKeyword, String> {

    List<TrendKeyword> findTop10TrendKeywordByOrderByCountDesc();
}
