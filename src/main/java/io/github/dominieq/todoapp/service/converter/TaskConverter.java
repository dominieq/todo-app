package io.github.dominieq.todoapp.service.converter;

import com.google.common.base.Converter;
import io.github.dominieq.todoapp.contract.dto.TaskDto;
import io.github.dominieq.todoapp.contract.dto.builder.TaskDtoBuilder;
import io.github.dominieq.todoapp.database.entity.TaskEntity;
import io.github.dominieq.todoapp.database.entity.builder.TaskEntityBuilder;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter extends Converter<TaskDto, TaskEntity> {

	@Override
	protected TaskEntity doForward(final TaskDto dto) {
		return TaskEntityBuilder.builder()
				.withId(dto.getId())
				.withDescription(dto.getDescription())
				.withDone(dto.getDone())
				.withDeadline(dto.getDeadline())
				.build();
	}

	@Override
	protected TaskDto doBackward(final TaskEntity entity) {
		return TaskDtoBuilder.builder()
				.withId(entity.getId())
				.withDescription(entity.getDescription())
				.withDone(entity.getDone())
				.withDeadline(entity.getDeadline())
				.build();
	}
}
