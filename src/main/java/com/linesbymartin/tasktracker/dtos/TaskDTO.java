package com.linesbymartin.tasktracker.dtos;

import com.linesbymartin.tasktracker.enums.TaskPriority;
import com.linesbymartin.tasktracker.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        Instant dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
