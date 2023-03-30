package io.github.dominieq.todoapp.contract.dto;

import com.google.common.base.MoreObjects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class TaskGroupDto implements Serializable {

	private static final long serialVersionUID = 7112553527875681201L;

	@Null(message = "TaskGroup.Id should be 'null'.")
	private Integer id;

	@NotBlank(message = "TaskGroup.Description shouldn't be empty.")
	private String description;

	@Null(message = "TaskGroup.Done should be 'null'.")
	private Boolean done;

	@Null(message = "TaskGroup.Deadline should be 'null'.")
	private LocalDateTime deadline;

	@NotEmpty(message = "TaskGroup.Tasks shouldn't be empty.")
	@Valid
	private Set<TaskDto> tasks;

	@SuppressWarnings("unused") // Empty constructor is for Jackson's ObjectMapper.
	protected TaskGroupDto() {
	}

	public TaskGroupDto(final Integer id,
						final String description,
						final Boolean done,
						final LocalDateTime deadline,
						final Set<TaskDto> tasks) {

		this.id = id;
		this.description = description;
		this.done = done;
		this.deadline = deadline;
		this.tasks = tasks;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final TaskGroupDto that = (TaskGroupDto) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description) &&
				Objects.equals(done, that.done) &&
				Objects.equals(deadline, that.deadline) &&
				Objects.equals(tasks, that.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, done, deadline, tasks);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("done", done)
				.add("deadline", deadline)
				.add("tasks", tasks)
				.toString();
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getDone() {
		return done;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public Set<TaskDto> getTasks() {
		return tasks;
	}
}
