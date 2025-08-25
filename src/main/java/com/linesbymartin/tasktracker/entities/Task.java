package com.linesbymartin.tasktracker.entities;

import com.linesbymartin.tasktracker.enums.TaskPriority;
import com.linesbymartin.tasktracker.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "priority", nullable = false)
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id", nullable = false)
    private TaskList taskList;

    @Column(name = "timestamp_create", nullable = false, updatable = false)
    private Instant timestampCreate;

    @Column(name = "timestamp_update")
    private Instant timestampUpdate;

    public Task() {
    }

    public Task(UUID id, String title, String description, Instant dueDate, TaskStatus status, TaskPriority priority, TaskList taskList, Instant timestampCreate, Instant timestampUpdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.taskList = taskList;
        this.timestampCreate = timestampCreate;
        this.timestampUpdate = timestampUpdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public Instant getTimestampCreate() {
        return timestampCreate;
    }

    public void setTimestampCreate(Instant timestampCreate) {
        this.timestampCreate = timestampCreate;
    }

    public Instant getTimestampUpdate() {
        return timestampUpdate;
    }

    public void setTimestampUpdate(Instant timestampUpdate) {
        this.timestampUpdate = timestampUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate) && status == task.status && priority == task.priority && Objects.equals(taskList, task.taskList) && Objects.equals(timestampCreate, task.timestampCreate) && Objects.equals(timestampUpdate, task.timestampUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status, priority, taskList, timestampCreate, timestampUpdate);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", taskList=" + taskList +
                ", timestampCreate=" + timestampCreate +
                ", timestampUpdate=" + timestampUpdate +
                '}';
    }
}
