package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class TaskEntity implements Serializable {

	private static final long serialVersionUID = 1025020152602626836L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@NotBlank(message = "TaskEntity.Description should not be empty.")
	private String description;

	@NotNull(message = "TaskEntity.Done should exist.")
	private Boolean done;

	private LocalDateTime deadline;

	@JsonIgnore
	private LocalDateTime createdOn;

	@JsonIgnore
	private LocalDateTime updatedOn;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected TaskEntity() {
	}

	public TaskEntity(final Integer id,
					  final String description,
					  final Boolean done,
					  final LocalDateTime deadline,
					  final LocalDateTime createdOn,
					  final LocalDateTime updatedOn) {

		this.id = id;
		this.description = description;
		this.done = done;
		this.deadline = deadline;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	@PrePersist
	void prePersist() {
		this.createdOn = LocalDateTime.now();
	}

	@PreUpdate
	void preUpdate() {
		this.updatedOn = LocalDateTime.now();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final TaskEntity that = (TaskEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description) &&
				Objects.equals(done, that.done) &&
				Objects.equals(deadline, that.deadline) &&
				Objects.equals(createdOn, that.createdOn) &&
				Objects.equals(updatedOn, that.updatedOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, done, deadline, createdOn, updatedOn);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("done", done)
				.add("deadline", deadline)
				.add("createdOn", createdOn)
				.add("updatedOn", updatedOn)
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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
}
