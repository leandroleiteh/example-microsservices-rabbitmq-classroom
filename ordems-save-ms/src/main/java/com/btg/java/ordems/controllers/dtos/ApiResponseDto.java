package com.btg.java.ordems.controllers.dtos;

import java.util.List;
import java.util.Map;

public record ApiResponseDto<T>(Map<String, Object> summary, List<T> data,
                                PaginationResponseDto paginationResponseDto) {
}
