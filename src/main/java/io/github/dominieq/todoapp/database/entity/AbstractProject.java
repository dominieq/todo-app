package io.github.dominieq.todoapp.database.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractProject implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;

	@NotBlank(message = "Project.Description cannot be empty.")
	protected String description;

	protected AbstractProject() {
	}

	protected AbstractProject(final Integer id,
							  final String description) {

		this.id = id;
		this.description = description;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final AbstractProject that = (AbstractProject) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description);
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
