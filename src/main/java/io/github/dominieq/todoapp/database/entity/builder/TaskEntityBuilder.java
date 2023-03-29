package io.github.dominieq.todoapp.database.entity.builder;

import io.github.dominieq.todoapp.database.entity.AuditEntity;
import io.github.dominieq.todoapp.database.entity.TaskEntity;

import java.time.LocalDateTime;

public final class TaskEntityBuilder {
	private Integer id;
	private String description;
	private Boolean done;
	private LocalDateTime deadline;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

	private TaskEntityBuilder() {
	}

	public static TaskEntityBuilder builder() {
		return new TaskEntityBuilder();
	}

	public TaskEntityBuilder from(final TaskEntity entity) {
		if (entity == null) {
			return this;
		}

		this.id = entity.getId();
		this.description = entity.getDescription();
		this.done = entity.getDone();
		this.deadline = entity.getDeadline();
		this.createdOn = entity.getCreatedOn();
		this.updatedOn = entity.getUpdatedOn();
		return this;
	}

	public TaskEntityBuilder withId(final Integer id) {
		this.id = id;
		return this;
	}

	public TaskEntityBuilder withDescription(final String description) {
		this.description = description;
		return this;
	}

	public TaskEntityBuilder withDone(final Boolean done) {
		this.done = done;
		return this;
	}

	public TaskEntityBuilder withDeadline(final LocalDateTime deadline) {
		this.deadline = deadline;
		return this;
	}

	public TaskEntityBuilder withCreatedOn(final LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public TaskEntityBuilder withUpdatedOn(final LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
		return this;
	}

	public TaskEntity build() {
		return new TaskEntity(id, description, done, deadline, new AuditEntity(createdOn, updatedOn));
	}
}
