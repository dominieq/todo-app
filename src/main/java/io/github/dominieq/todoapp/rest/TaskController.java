package io.github.dominieq.todoapp.rest;

import io.github.dominieq.todoapp.database.entity.TaskEntity;
import io.github.dominieq.todoapp.database.entity.builder.TaskEntityBuilder;
import io.github.dominieq.todoapp.database.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
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

	@GetMapping("/tasks/{id}")
	ResponseEntity<TaskEntity> readTask(@PathVariable final int id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@PostMapping("/tasks")
	ResponseEntity<TaskEntity> createTask(@RequestBody @Valid final TaskEntity entity) {
		final TaskEntity created = repository.save(entity);
		final URI uri = URI.create("/tasks/" + created.getId());
		return ResponseEntity.created(uri).body(created);
	}

	@PutMapping("/tasks/{id}")
	ResponseEntity<Void> updateTask(@PathVariable final int id,
									@RequestBody @Valid final TaskEntity entity) {

		if (repository.existsById(id)) {
			final TaskEntity toUpdate = TaskEntityBuilder.builder()
					.from(entity)
					.withId(id)
					.build();

			repository.save(toUpdate);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}
