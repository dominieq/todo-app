package io.github.dominieq.todoapp.database.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class TaskEntity extends BaseEntity {

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

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected TaskEntity() {
	}

	public TaskEntity(final Integer id,
					  final String description,
					  final Boolean done,
					  final LocalDateTime deadline,
					  final LocalDateTime createdOn,
					  final LocalDateTime updatedOn) {

		super(createdOn, updatedOn);

		this.id = id;
		this.description = description;
		this.done = done;
		this.deadline = deadline;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		final TaskEntity that = (TaskEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description) &&
				Objects.equals(done, that.done) &&
				Objects.equals(deadline, that.deadline);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, description, done, deadline);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("deadline", deadline)
				.add("created_on", createdOn)
				.add("updated_on", updatedOn)
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
