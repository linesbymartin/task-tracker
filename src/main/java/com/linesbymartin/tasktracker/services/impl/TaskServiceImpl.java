package com.linesbymartin.tasktracker.services.impl;

import com.linesbymartin.tasktracker.entities.Task;
import com.linesbymartin.tasktracker.entities.TaskList;
import com.linesbymartin.tasktracker.enums.TaskPriority;
import com.linesbymartin.tasktracker.enums.TaskStatus;
import com.linesbymartin.tasktracker.repositories.TaskListRepository;
import com.linesbymartin.tasktracker.repositories.TaskRepository;
import com.linesbymartin.tasktracker.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    @Transactional
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if (task.getDueDate() != null && task.getDueDate().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Due date cannot be in the past");
        }

        TaskList dbTaskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("TaskList not found"));

        Task taskToSave = new Task();
        taskToSave.setTitle(task.getTitle().trim());
        taskToSave.setDescription(task.getDescription());
        taskToSave.setDueDate(task.getDueDate());
        taskToSave.setPriority(task.getPriority() != null ? task.getPriority() : TaskPriority.MEDIUM);
        taskToSave.setStatus(TaskStatus.OPEN);
        taskToSave.setTimestampCreate(Instant.now());

        taskToSave.setTaskList(dbTaskList);
        if (dbTaskList.getTasks() == null) {
            dbTaskList.setTasks(new ArrayList<>());
        }
        dbTaskList.getTasks().add(taskToSave);

        dbTaskList.setTimestampUpdate(Instant.now());

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID id) {
        return taskRepository.findByTaskListIdAndId(taskListId, id);
    }

    @Override
    @Transactional
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task must have an ID!");
        }

        if (!taskId.equals(task.getId())) {
            throw new IllegalArgumentException("Changing Task ID is not permitted!");
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }

        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null");
        }

        Task dbTask = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        dbTask.setTitle(task.getTitle().trim());
        dbTask.setDescription(task.getDescription().trim());
        dbTask.setDueDate(task.getDueDate());
        dbTask.setPriority(task.getPriority());
        dbTask.setStatus(task.getStatus());
        dbTask.setTimestampUpdate(Instant.now());
        return taskRepository.save(dbTask);
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskListId, UUID taskId) {
       taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }

}
