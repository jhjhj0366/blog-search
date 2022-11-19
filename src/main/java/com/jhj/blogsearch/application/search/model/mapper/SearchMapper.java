package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;

public interface SearchMapper<T> {

    SearchDTO.Res mapper(T response);
}