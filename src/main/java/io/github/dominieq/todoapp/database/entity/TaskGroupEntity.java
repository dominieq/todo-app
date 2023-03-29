package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroupEntity extends AbstractTask {

	private static final long serialVersionUID = -6638881662061913498L;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private Set<TaskEntity> tasks;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	public TaskGroupEntity() {
	}

	public TaskGroupEntity(final Integer id,
						   final String description,
						   final Boolean done,
						   final AuditEntity audit,
						   final Set<TaskEntity> tasks) {

		super(id, description, done, audit);

		this.tasks = tasks;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		final TaskGroupEntity that = (TaskGroupEntity) o;
		return Objects.equals(tasks, that.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), tasks);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("done", done)
				.add("createdOn", audit.getCreatedOn())
				.add("updatedOn", audit.getUpdatedOn())
				.add("tasks", tasks)
				.toString();
	}

	public Set<TaskEntity> getTasks() {
		return tasks;
	}
}
