package io.github.dominieq.todoapp.service.converter;

import io.github.dominieq.todoapp.contract.dto.TaskGroupDto;
import io.github.dominieq.todoapp.contract.dto.builder.TaskDtoBuilder;
import io.github.dominieq.todoapp.contract.dto.builder.TaskGroupDtoBuilder;
import io.github.dominieq.todoapp.database.entity.TaskGroupEntity;
import io.github.dominieq.todoapp.database.entity.builder.TaskEntityBuilder;
import io.github.dominieq.todoapp.database.entity.builder.TaskGroupEntityBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TaskGroupConverterTest {

	private static TaskGroupDto dto;
	private static TaskGroupEntity entity;
	private TaskGroupConverter subject;

	@BeforeAll
	static void initialize() {
		dto = TaskGroupDtoBuilder.builder()
				.withId(1)
				.withDescription("Converter Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T18:30:00"))
				.withTasks(Set.of(
						TaskDtoBuilder.builder()
								.withId(1)
								.withDescription("Converter Test")
								.withDone(true)
								.withDeadline(LocalDateTime.parse("2023-03-30T18:30:00"))
								.build()))
				.build();

		entity = TaskGroupEntityBuilder.builder()
				.withId(1)
				.withDescription("Converter Test")
				.withDone(true)
				.withTasks(Set.of(
						TaskEntityBuilder.builder()
								.withId(1)
								.withDescription("Converter Test")
								.withDone(true)
								.withDeadline(LocalDateTime.parse("2023-03-30T18:30:00"))
								.build()))
				.build();
	}

	@BeforeEach
	void setUp() {
		subject = new TaskGroupConverter(new TaskConverter());
	}

	@Test
	void should_convert_full_dto_to_entity() {

		// when
		final TaskGroupEntity actual = subject.convert(dto);

		// then
		assertThat(actual).isEqualTo(entity);
	}

	@Test
	void should_convert_dto_without_tasks_to_entity() {

		// given
		final TaskGroupDto dtoWithoutTasks = TaskGroupDtoBuilder.builder()
				.from(dto)
				.withTasks(Set.of())
				.build();

		// when
		final TaskGroupEntity actual = subject.convert(dtoWithoutTasks);

		// then
		final TaskGroupEntity expected = TaskGroupEntityBuilder.builder()
				.from(entity)
				.withTasks(Set.of())
				.build();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void should_convert_empty_dto_to_entity() {

		// when
		final TaskGroupEntity actual = subject.convert(TaskGroupDtoBuilder.builder().build());

		// then
		assertThat(actual).hasAllNullFieldsOrPropertiesExcept("audit");
	}

	@Test
	void should_convert_null_dto_to_entity() {

		// when
		final TaskGroupEntity actual = subject.convert(null);

		// then
		assertThat(actual).isNull();
	}

	@Test
	void should_convert_full_entity_to_dto() {

		// when
		final TaskGroupDto actual = subject.reverse().convert(entity);

		// then
		assertThat(actual).isEqualTo(dto);
	}

	@Test
	void should_convert_entity_without_tasks_to_dto() {

		// given
		final TaskGroupEntity entityWithoutTasks = TaskGroupEntityBuilder.builder()
				.from(entity)
				.withTasks(Set.of())
				.build();

		// when
		final TaskGroupDto actual = subject.reverse().convert(entityWithoutTasks);

		// then
		final TaskGroupDto expected = TaskGroupDtoBuilder.builder()
				.from(dto)
				.withDeadline(null)
				.withTasks(Set.of())
				.build();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void should_convert_empty_entity_to_dto() {

		// when
		final TaskGroupDto actual = subject.reverse().convert(TaskGroupEntityBuilder.builder().build());

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
	}

	@Test
	void should_convert_null_entity_to_dto() {

		// when
		final TaskGroupDto actual = subject.reverse().convert(null);

		// then
		assertThat(actual).isNull();
	}
}
