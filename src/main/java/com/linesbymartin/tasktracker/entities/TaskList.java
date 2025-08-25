package com.linesbymartin.tasktracker.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Task> tasks;

    @Column(name = "timestamp_create", nullable = false, updatable = false)
    private Instant timestampCreate;

    @Column(name = "timestamp_update")
    private Instant timestampUpdate;

    public TaskList() {
    }

    public TaskList(UUID id, String title, String description, List<Task> tasks, Instant timestampCreate, Instant timestampUpdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id) && Objects.equals(title, taskList.title) && Objects.equals(description, taskList.description) && Objects.equals(tasks, taskList.tasks) && Objects.equals(timestampCreate, taskList.timestampCreate) && Objects.equals(timestampUpdate, taskList.timestampUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, timestampCreate, timestampUpdate);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", timestampCreate=" + timestampCreate +
                ", timestampUpdate=" + timestampUpdate +
                '}';
    }
}
