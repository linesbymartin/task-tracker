package com.linesbymartin.tasktracker.controllers;

import com.linesbymartin.tasktracker.dtos.TaskListDTO;
import com.linesbymartin.tasktracker.entities.TaskList;
import com.linesbymartin.tasktracker.mappers.TaskListMapper;
import com.linesbymartin.tasktracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDTO> listTaskLists() {
        return taskListService.listTaskLists().stream().map(taskListMapper::toDto).toList();
    }

    @PostMapping
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
        TaskList createdTask = taskListService.createTaskList(taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(createdTask);
    }
}
