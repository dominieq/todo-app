package io.github.dominieq.todoapp.database.repository.jpa;

import io.github.dominieq.todoapp.database.entity.ProjectEntity;
import io.github.dominieq.todoapp.database.repository.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SqlProjectRepository extends ProjectRepository, JpaRepository<ProjectEntity, Integer> {

	@Query("from ProjectEntity p join fetch p.steps")
	@Override
	List<ProjectEntity> findAll();
}
