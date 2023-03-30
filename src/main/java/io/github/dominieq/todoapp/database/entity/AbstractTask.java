package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractTask implements Audit, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;

	@NotBlank(message = "TaskEntity.Description should not be empty.")
	protected String description;

	@NotNull(message = "TaskEntity.Done should exist.")
	protected Boolean done;

	@JsonIgnore
	@Embedded
	protected AuditEntity audit = new AuditEntity();

	protected AbstractTask() {
	}

	protected AbstractTask(final Integer id,
						   final String description,
						   final Boolean done,
						   final AuditEntity audit) {

		this.id = id;
		this.description = description;
		this.done = done;
		this.audit = audit;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final AbstractTask that = (AbstractTask) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description) &&
				Objects.equals(done, that.done) &&
				Objects.equals(audit, that.audit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, done, audit);
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
}
