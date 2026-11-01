package com.example.jpaexample.dto;

public record BookSaveRequestDto(
        Long authorId,
        String title,
        String genre
) {
}