package io.github.dominieq.todoapp.rest;

import io.github.dominieq.todoapp.database.entity.TaskEntity;
import io.github.dominieq.todoapp.database.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RepositoryRestController
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	private final TaskRepository repository;

	public TaskController(final TaskRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/tasks", params = {"!sort", "!page", "!size"})
	public ResponseEntity<List<TaskEntity>> readAllTasks() {
		LOGGER.info("Reading all tasks.");
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskEntity>> readAllTasks(Pageable pageable) {
		LOGGER.info("Reading all tasks with paging");
		return ResponseEntity.ok(repository.findAll(pageable).getContent());
	}
}
