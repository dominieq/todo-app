package io.github.dominieq.todoapp.database.repository;

import io.github.dominieq.todoapp.database.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

	List<TaskEntity> findAll();

	Page<TaskEntity> findAll(Pageable pageable);

	Optional<TaskEntity> findById(Integer id);

	List<TaskEntity> findByDone(boolean done);

	boolean existsById(Integer id);

	TaskEntity save(TaskEntity entity);

}
