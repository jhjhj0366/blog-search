package com.jhj.blogsearch.api;

import com.jhj.blogsearch.api.dto.TrendKeywordDTO;
import com.jhj.blogsearch.application.trend.TrendKeywordService;
import com.jhj.blogsearch.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trend")
public class TrendKeywordController {

    private final TrendKeywordService trendKeywordService;

    @Operation(summary = "Trend Search API", description = "Get the Top 10 Trend Keyword")
    @GetMapping(value = "/rank")
    public ApiResponse<List<TrendKeywordDTO>> searchRank() {
        return ApiResponse.success(trendKeywordService.getTop10TrendKeywordsLookAside());
    }
}