package com.jhj.blogsearch.api;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.BlogSearchService;
import com.jhj.blogsearch.application.search.model.SearchPage;
import com.jhj.blogsearch.application.search.model.SortType;
import com.jhj.blogsearch.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @Operation(summary = "Blog Search API", description = "Blog Search API with OPEN API")
    @GetMapping("/blog")
    public ApiResponse<SearchPage> searchBlog(@RequestParam(name = "query") String query,
                                                @RequestParam(name = "sort", defaultValue = "accuracy") String sort,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "30") int size) {

        SearchPage searchPage = blogSearchService.searchBlog(SearchDTO.Req.of(query, SortType.getKakaoSortType(sort), page, size));
        return ApiResponse.success(searchPage);
    }

}