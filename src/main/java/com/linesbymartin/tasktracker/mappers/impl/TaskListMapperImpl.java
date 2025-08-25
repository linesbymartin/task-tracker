package com.linesbymartin.tasktracker.mappers.impl;

import com.linesbymartin.tasktracker.dtos.TaskListDTO;
import com.linesbymartin.tasktracker.entities.Task;
import com.linesbymartin.tasktracker.entities.TaskList;
import com.linesbymartin.tasktracker.enums.TaskStatus;
import com.linesbymartin.tasktracker.mappers.TaskListMapper;
import com.linesbymartin.tasktracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDTO taskListDTO) {
        return new TaskList(
                taskListDTO.id(),
                taskListDTO.title(),
                taskListDTO.description(),
                Optional.ofNullable(taskListDTO.tasks()).map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks) return null;
        long closedTasksCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();
        return (double) closedTasksCount / tasks.size();
    }
}
