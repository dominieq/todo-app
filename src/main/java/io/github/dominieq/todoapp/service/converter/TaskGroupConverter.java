package io.github.dominieq.todoapp.service.converter;

import com.google.common.base.Converter;
import io.github.dominieq.todoapp.contract.dto.TaskDto;
import io.github.dominieq.todoapp.contract.dto.TaskGroupDto;
import io.github.dominieq.todoapp.contract.dto.builder.TaskGroupDtoBuilder;
import io.github.dominieq.todoapp.database.entity.TaskEntity;
import io.github.dominieq.todoapp.database.entity.TaskGroupEntity;
import io.github.dominieq.todoapp.database.entity.builder.TaskGroupEntityBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TaskGroupConverter extends Converter<TaskGroupDto, TaskGroupEntity> {

	private final TaskConverter taskConverter;

	public TaskGroupConverter(final TaskConverter taskConverter) {
		this.taskConverter = taskConverter;
	}

	@Override
	protected TaskGroupEntity doForward(final TaskGroupDto dto) {
		return TaskGroupEntityBuilder.builder()
				.withId(dto.getId())
				.withDescription(dto.getDescription())
				.withDone(dto.getDone())
				.withTasks(convertToEntity(dto.getTasks()))
				.build();
	}

	@Override
	protected TaskGroupDto doBackward(final TaskGroupEntity entity) {
		return TaskGroupDtoBuilder.builder()
				.withId(entity.getId())
				.withDescription(entity.getDescription())
				.withDone(entity.getDone())
				.withDeadline(calculateDeadline(entity.getTasks()))
				.withTasks(convertToDto(entity.getTasks()))
				.build();
	}

	Set<TaskEntity> convertToEntity(final Set<TaskDto> tasks) {
		if (tasks == null) return null;
		return tasks.stream()
				.map(taskConverter::convert)
				.collect(Collectors.toSet());
	}

	LocalDateTime calculateDeadline(final Set<TaskEntity> tasks) {
		if (tasks == null) return null;
		return tasks.stream()
				.map(TaskEntity::getDeadline)
				.sorted()
				.findFirst()
				.orElse(null);
	}

	Set<TaskDto> convertToDto(final Set<TaskEntity> tasks) {
		if (tasks == null) return null;
		return tasks.stream()
				.map(taskConverter.reverse()::convert)
				.collect(Collectors.toSet());
	}
}
