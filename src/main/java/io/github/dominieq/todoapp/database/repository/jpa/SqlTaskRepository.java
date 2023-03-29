package io.github.dominieq.todoapp.database.repository;

import io.github.dominieq.todoapp.database.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<TaskEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM TASKS WHERE ID=:id")
	@Override
	boolean existsById(@Param("id") Integer id);

	@Override
	boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);
}
