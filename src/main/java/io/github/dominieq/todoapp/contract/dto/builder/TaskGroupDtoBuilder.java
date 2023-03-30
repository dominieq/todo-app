package io.github.dominieq.todoapp.contract.dto.builder;

import io.github.dominieq.todoapp.contract.dto.TaskDto;
import io.github.dominieq.todoapp.contract.dto.TaskGroupDto;

import java.time.LocalDateTime;
import java.util.Set;

public final class TaskGroupDtoBuilder {

	private Integer id;
	private String description;
	private Boolean done;
	private LocalDateTime deadline;
	private Set<TaskDto> tasks;

	private TaskGroupDtoBuilder() {
	}

	public static TaskGroupDtoBuilder builder() {
		return new TaskGroupDtoBuilder();
	}

	public TaskGroupDtoBuilder withId(final Integer id) {
		this.id = id;
		return this;
	}

	public TaskGroupDtoBuilder withDescription(final String description) {
		this.description = description;
		return this;
	}

	public TaskGroupDtoBuilder withDone(final Boolean done) {
		this.done = done;
		return this;
	}

	public TaskGroupDtoBuilder withDeadline(final LocalDateTime deadline) {
		this.deadline = deadline;
		return this;
	}

	public TaskGroupDtoBuilder withTasks(final Set<TaskDto> tasks) {
		this.tasks = tasks;
		return this;
	}

	public TaskGroupDto build() {
		return new TaskGroupDto(id, description, done, deadline, tasks);
	}
}
