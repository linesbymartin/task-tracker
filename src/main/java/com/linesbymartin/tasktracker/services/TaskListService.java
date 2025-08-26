package com.linesbymartin.tasktracker.services;

import com.linesbymartin.tasktracker.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList taskList);
}
