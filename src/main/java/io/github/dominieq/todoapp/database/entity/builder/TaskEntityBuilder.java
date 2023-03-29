package io.github.dominieq.todoapp.database.entity.builder;

import io.github.dominieq.todoapp.database.entity.TaskEntity;

public final class TaskEntityBuilder {
	private Integer id;
	private String description;
	private Boolean done;

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

	public TaskEntity build() {
		return new TaskEntity(id, description, done);
	}
}
