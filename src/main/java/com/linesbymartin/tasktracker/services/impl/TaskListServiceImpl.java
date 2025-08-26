package com.linesbymartin.tasktracker.services.impl;

import com.linesbymartin.tasktracker.entities.TaskList;
import com.linesbymartin.tasktracker.repositories.TaskListRepository;
import com.linesbymartin.tasktracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()){
            throw new IllegalArgumentException("TaskList already has an ID!");
        }
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("TaskList must have a title!");
        }

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                Instant.now(),
                null
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

}
