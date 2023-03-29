package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroupEntity implements Audit, Serializable {

	private static final long serialVersionUID = -6638881662061913498L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@NotBlank(message = "TaskEntity.Description should not be empty.")
	private String description;

	@NotNull(message = "TaskEntity.Done should exist.")
	private Boolean done;

	@JsonIgnore
	@Embedded
	private AuditEntity audit = new AuditEntity();

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

		this.id = id;
		this.description = description;
		this.done = done;
		this.audit = audit;
		this.tasks = tasks;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final TaskGroupEntity that = (TaskGroupEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description) &&
				Objects.equals(done, that.done) &&
				Objects.equals(audit, that.audit) &&
				Objects.equals(tasks, that.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, done, audit, tasks);
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

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getDone() {
		return done;
	}

	@JsonIgnore
	@Override
	public LocalDateTime getCreatedOn() {
		return audit.getCreatedOn();
	}

	@JsonIgnore
	@Override
	public LocalDateTime getUpdatedOn() {
		return audit.getUpdatedOn();
	}

	public Set<TaskEntity> getTasks() {
		return tasks;
	}
}
