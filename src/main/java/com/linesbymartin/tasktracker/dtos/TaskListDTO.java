package com.linesbymartin.tasktracker.dtos;

import java.util.List;
import java.util.UUID;

public record TaskListDTO(
        UUID id,
        String title,
        String description,
        Integer taskCount,
        Double taskProgress,
        List<TaskDTO> tasks
) {
}
