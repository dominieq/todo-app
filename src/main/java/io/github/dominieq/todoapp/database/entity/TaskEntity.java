package io.github.dominieq.todoapp.database.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class TaskEntity implements Serializable {

	private static final long serialVersionUID = 1025020152602626836L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String description;
	private Boolean done;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected TaskEntity() {
	}

	public TaskEntity(final Integer id,
					  final String description,
					  final Boolean done) {

		this.id = id;
		this.description = description;
		this.done = done;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final TaskEntity that = (TaskEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description) &&
				Objects.equals(done, that.done);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, done);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("done", done)
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
}
