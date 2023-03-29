package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class TaskEntity extends AbstractTask {

	private static final long serialVersionUID = 1025020152602626836L;

	private LocalDateTime deadline;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "task_group_id")
	private TaskGroupEntity group;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected TaskEntity() {
	}

	public TaskEntity(final Integer id,
					  final String description,
					  final Boolean done,
					  final LocalDateTime deadline,
					  final AuditEntity audit,
					  final TaskGroupEntity group) {

		super(id, description, done, audit);

		this.deadline = deadline;
		this.group = group;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		final TaskEntity that = (TaskEntity) o;
		return Objects.equals(deadline, that.deadline);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), deadline);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("deadline", deadline)
				.add("created_on", audit.getCreatedOn())
				.add("updated_on", audit.getUpdatedOn())
				.add("group", group)
				.toString();
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public TaskGroupEntity getGroup() {
		return group;
	}
}
