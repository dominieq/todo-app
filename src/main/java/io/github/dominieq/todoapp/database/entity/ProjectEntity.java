package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectEntity extends AbstractProject {

	private static final long serialVersionUID = -828861400872961098L;

	@JsonIgnore
	@OneToMany(mappedBy = "project")
	private Set<TaskGroupEntity> groups;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private Set<ProjectStepsEntity> steps;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected ProjectEntity() {
	}

	public ProjectEntity(final Integer id,
						 final String description,
						 final Set<TaskGroupEntity> groups,
						 final Set<ProjectStepsEntity> steps) {

		super(id, description);

		this.groups = groups;
		this.steps = steps;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		final ProjectEntity that = (ProjectEntity) o;
		return Objects.equals(steps, that.steps);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), steps);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("groups", groups)
				.add("steps", steps)
				.toString();
	}

	public Set<TaskGroupEntity> getGroups() {
		return groups;
	}

	public Set<ProjectStepsEntity> getSteps() {
		return steps;
	}
}
