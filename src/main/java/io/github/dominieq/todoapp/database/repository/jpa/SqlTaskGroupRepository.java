package io.github.dominieq.todoapp.database.repository.jpa;

import io.github.dominieq.todoapp.database.entity.TaskGroupEntity;
import io.github.dominieq.todoapp.database.repository.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroupEntity, Integer> {

	@Query("select distinct g from TaskGroupEntity g left join fetch g.tasks") // Solve 'n + 1 selects' problems
	@Override
	List<TaskGroupEntity> findAll();

	@Override
	boolean existsByDoneIsFalseAndProject_Id(Integer id);
}
