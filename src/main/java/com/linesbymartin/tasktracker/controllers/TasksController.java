package com.linesbymartin.tasktracker.controllers;

import com.linesbymartin.tasktracker.dtos.TaskDTO;
import com.linesbymartin.tasktracker.mappers.TaskMapper;
import com.linesbymartin.tasktracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDTO> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDTO createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDTO taskDTO) {
        return taskMapper.toDto(taskService.createTask(taskListId, taskMapper.fromDto(taskDTO)));
    }

    @GetMapping("/{task_id}")
    public TaskDTO getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
            return taskService.getTask(taskListId, taskId).map(taskMapper::toDto).orElse(null);
    }
}
