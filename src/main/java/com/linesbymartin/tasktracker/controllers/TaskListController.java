package com.linesbymartin.tasktracker.controllers;

import com.linesbymartin.tasktracker.dtos.TaskListDTO;
import com.linesbymartin.tasktracker.entities.TaskList;
import com.linesbymartin.tasktracker.mappers.TaskListMapper;
import com.linesbymartin.tasktracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDTO> getTaskList(@PathVariable("task_list_id") UUID id) {
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PutMapping("/{task_list_id}")
    public TaskListDTO updateTaskList(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskListDTO taskListDTO) {
        TaskList updatedTask = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(updatedTask);
    }
}
