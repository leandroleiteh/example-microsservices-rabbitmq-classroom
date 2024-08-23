package com.btg.java.ordems.controllers.dtos;

import org.springframework.data.domain.Page;

public record PaginationResponseDto(Integer page,
                                    Integer pageSize,
                                    Integer totalElements,
                                    Integer totalPages) {

    public static PaginationResponseDto convertPaganation(Page<?> page){
        return new PaginationResponseDto(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalPages()
        );
    }
}
