package io.github.dominieq.todoapp.database.repository;

import io.github.dominieq.todoapp.database.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "tasks", collectionResourceRel = "tasks")
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

	@Override
	@RestResource(exported = false)
	void deleteById(Integer integer);

	@Override
	@RestResource(exported = false)
	void delete(TaskEntity entity);
}
