package com.linesbymartin.tasktracker.mappers;

import com.linesbymartin.tasktracker.dtos.TaskDTO;
import com.linesbymartin.tasktracker.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDTO taskDTO);

    TaskDTO toDto(Task task);
}
