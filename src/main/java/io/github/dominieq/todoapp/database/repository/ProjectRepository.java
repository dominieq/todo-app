package io.github.dominieq.todoapp.database.repository;

import io.github.dominieq.todoapp.database.entity.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

	List<ProjectEntity> findAll();

	Optional<ProjectEntity> findById(Integer id);

	ProjectEntity save(ProjectEntity entity);
}
