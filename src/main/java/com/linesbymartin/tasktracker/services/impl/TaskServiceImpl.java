package com.linesbymartin.tasktracker.services.impl;

import com.linesbymartin.tasktracker.entities.Task;
import com.linesbymartin.tasktracker.repositories.TaskRepository;
import com.linesbymartin.tasktracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }
}
