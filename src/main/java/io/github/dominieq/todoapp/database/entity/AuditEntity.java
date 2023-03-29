package io.github.dominieq.todoapp.database.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AuditEntity implements Audit, Serializable {

	private static final long serialVersionUID = 7154743097224259172L;

	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected AuditEntity() {
	}

	public AuditEntity(final LocalDateTime createdOn,
					   final LocalDateTime updatedOn) {

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
		final AuditEntity that = (AuditEntity) o;
		return Objects.equals(createdOn, that.createdOn) &&
				Objects.equals(updatedOn, that.updatedOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdOn, updatedOn);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("createdOn", createdOn)
				.add("updatedOn", updatedOn)
				.toString();
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
}
