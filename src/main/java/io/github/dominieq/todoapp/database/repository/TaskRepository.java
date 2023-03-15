package io.github.dominieq.todoapp.database.repository;

import io.github.dominieq.todoapp.database.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}
