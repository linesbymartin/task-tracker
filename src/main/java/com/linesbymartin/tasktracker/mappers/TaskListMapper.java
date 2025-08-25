package com.linesbymartin.tasktracker.mappers;

import com.linesbymartin.tasktracker.dtos.TaskListDTO;
import com.linesbymartin.tasktracker.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDTO taskListDTO);

    TaskListDTO toDto(TaskList taskList);
}
