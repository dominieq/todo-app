package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@JsonIgnore
	protected LocalDateTime createdOn;
	@JsonIgnore
	protected LocalDateTime updatedOn;

	protected BaseEntity() {
	}

	protected BaseEntity(final LocalDateTime createdOn,
						 final LocalDateTime updatedOn) {

		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	@PrePersist
	protected void prePersist() {
		this.createdOn = LocalDateTime.now();
	}

	@PreUpdate
	protected void preUpdate() {
		this.updatedOn = LocalDateTime.now();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final TaskEntity that = (TaskEntity) o;
		return Objects.equals(createdOn, that.createdOn) &&
				Objects.equals(updatedOn, that.updatedOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdOn, updatedOn);
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
}
