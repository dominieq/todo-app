package io.github.dominieq.todoapp.database.entity.builder;

import io.github.dominieq.todoapp.database.entity.AuditEntity;
import io.github.dominieq.todoapp.database.entity.ProjectEntity;
import io.github.dominieq.todoapp.database.entity.TaskEntity;
import io.github.dominieq.todoapp.database.entity.TaskGroupEntity;

import java.time.LocalDateTime;
import java.util.Set;

public final class TaskGroupEntityBuilder {

	private Integer id;
	private String description;
	private Boolean done;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private Set<TaskEntity> tasks;
	private ProjectEntity project;

	private TaskGroupEntityBuilder() {
	}

	public static TaskGroupEntityBuilder builder() {
		return new TaskGroupEntityBuilder();
	}

	public TaskGroupEntityBuilder from(final TaskGroupEntity entity) {
		if (entity == null) {
			return this;
		}

		this.id = entity.getId();
		this.description = entity.getDescription();
		this.done = entity.getDone();
		this.createdOn = entity.getCreatedOn();
		this.updatedOn = entity.getUpdatedOn();
		this.tasks = entity.getTasks();
		this.project = entity.getProject();
		return this;
	}

	public TaskGroupEntityBuilder withId(final Integer id) {
		this.id = id;
		return this;
	}

	public TaskGroupEntityBuilder withDescription(final String description) {
		this.description = description;
		return this;
	}

	public TaskGroupEntityBuilder withDone(final Boolean done) {
		this.done = done;
		return this;
	}

	public TaskGroupEntityBuilder withCreatedOn(final LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public TaskGroupEntityBuilder withUpdatedOn(final LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
		return this;
	}

	public TaskGroupEntityBuilder withTasks(final Set<TaskEntity> tasks) {
		this.tasks = tasks;
		return this;
	}

	public TaskGroupEntityBuilder withProject(final ProjectEntity project) {
		this.project = project;
		return this;
	}

	public TaskGroupEntity build() {
		return new TaskGroupEntity(
				id,
				description,
				done,
				new AuditEntity(createdOn, updatedOn),
				tasks,
				project);
	}
}
