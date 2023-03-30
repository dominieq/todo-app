package io.github.dominieq.todoapp.contract.dto;

import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TaskDto implements Serializable {

	private static final long serialVersionUID = -7162955793825070556L;

	@Null(message = "Task.Id shouldn't exist.")
	private Integer id;

	@NotBlank(message = "Task.Description shouldn't be empty.")
	private String description;

	@Null(message = "Task.Done shouldn't exist.")
	private Boolean done;

	@NotNull(message = "Task.Deadline shouldn't be null.")
	private LocalDateTime deadline;

	@SuppressWarnings("unused") // Empty constructor is for Jackson's ObjectMapper.
	protected TaskDto() {
	}

	public TaskDto(final Integer id,
				   final String description,
				   final Boolean done,
				   final LocalDateTime deadline) {

		this.id = id;
		this.description = description;
		this.done = done;
		this.deadline = deadline;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final TaskDto taskDto = (TaskDto) o;
		return Objects.equals(id, taskDto.id) &&
				Objects.equals(description, taskDto.description) &&
				Objects.equals(done, taskDto.done) &&
				Objects.equals(deadline, taskDto.deadline);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, done, deadline);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("done", done)
				.add("deadline", deadline)
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
}
