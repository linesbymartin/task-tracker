package com.linesbymartin.tasktracker.services;

import com.linesbymartin.tasktracker.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
}
