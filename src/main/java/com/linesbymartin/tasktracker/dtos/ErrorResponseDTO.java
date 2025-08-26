package com.linesbymartin.tasktracker.dtos;

public record ErrorResponseDTO(
        int status,
        String message,
        String details) {
}
