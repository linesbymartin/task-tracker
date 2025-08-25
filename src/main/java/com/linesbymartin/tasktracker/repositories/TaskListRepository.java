package com.linesbymartin.tasktracker.repositories;

import com.linesbymartin.tasktracker.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
