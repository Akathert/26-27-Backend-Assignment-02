package com.example.jpaexample.dto;

import com.example.jpaexample.domain.Author;
import lombok.Builder;

@Builder
public record AuthorInfoResponseDto(
        Long id,
        String name,
        String nationality
) {
    public static AuthorInfoResponseDto from(Author author) {
        return AuthorInfoResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .nationality(author.getNationality())
                .build();
    }
}