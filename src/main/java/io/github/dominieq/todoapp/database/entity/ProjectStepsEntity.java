package io.github.dominieq.todoapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "project_steps")
public class ProjectStepsEntity extends AbstractProject {

	private static final long serialVersionUID = 4527018883718962706L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "project_id")
	private ProjectEntity project;

	private Integer daysToDeadline;

	@SuppressWarnings("unused") // Empty constructor is for CDI purpose.
	protected ProjectStepsEntity() {
	}

	public ProjectStepsEntity(final Integer id,
							  final String description,
							  final ProjectEntity project,
							  final Integer daysToDeadline) {

		super(id, description);

		this.project = project;
		this.daysToDeadline = daysToDeadline;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		final ProjectStepsEntity that = (ProjectStepsEntity) o;
		return Objects.equals(daysToDeadline, that.daysToDeadline);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), daysToDeadline);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("description", description)
				.add("project", project)
				.add("daysToDeadline", daysToDeadline)
				.toString();
	}

	public ProjectEntity getProject() {
		return project;
	}

	public Integer getDaysToDeadline() {
		return daysToDeadline;
	}
}
