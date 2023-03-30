package io.github.dominieq.todoapp.contract.dto.builder;

import io.github.dominieq.todoapp.contract.dto.TaskDto;

import java.time.LocalDateTime;

public final class TaskDtoBuilder {

	private Integer id;
	private String description;
	private Boolean done;
	private LocalDateTime deadline;

	private TaskDtoBuilder() {
	}

	public static TaskDtoBuilder builder() {
		return new TaskDtoBuilder();
	}

	public TaskDtoBuilder withId(final Integer id) {
		this.id = id;
		return this;
	}

	public TaskDtoBuilder withDescription(final String description) {
		this.description = description;
		return this;
	}

	public TaskDtoBuilder withDone(final Boolean done) {
		this.done = done;
		return this;
	}

	public TaskDtoBuilder withDeadline(final LocalDateTime deadline) {
		this.deadline = deadline;
		return this;
	}

	public TaskDto build() {
		return new TaskDto(id, description, done, deadline);
	}
}
