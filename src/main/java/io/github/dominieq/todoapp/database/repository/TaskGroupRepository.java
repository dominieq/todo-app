package io.github.dominieq.todoapp.database.repository;

import io.github.dominieq.todoapp.database.entity.TaskGroupEntity;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {

	List<TaskGroupEntity> findAll();

	Optional<TaskGroupEntity> findById(Integer id);

	boolean existsByDoneIsFalseAndProject_Id(Integer id);

	TaskGroupEntity save(TaskGroupEntity entity);
}
