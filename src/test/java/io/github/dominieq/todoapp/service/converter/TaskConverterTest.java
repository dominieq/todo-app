package io.github.dominieq.todoapp.service.converter;

import io.github.dominieq.todoapp.contract.dto.TaskDto;
import io.github.dominieq.todoapp.contract.dto.builder.TaskDtoBuilder;
import io.github.dominieq.todoapp.database.entity.TaskEntity;
import io.github.dominieq.todoapp.database.entity.builder.TaskEntityBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TaskConverterTest {

	private static TaskDto dto;
	private static TaskEntity entity;

	private TaskConverter subject;

	@BeforeAll
	static void initialize() {
		dto = TaskDtoBuilder.builder()
				.withId(1)
				.withDescription("Converter Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T13:50:00"))
				.build();

		entity = TaskEntityBuilder.builder()
				.withId(1)
				.withDescription("Converter Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T13:50:00"))
				.build();
	}
	@BeforeEach
	void setUp() {
		subject = new TaskConverter();
	}

	@Test
	void should_convert_full_dto_to_entity() {

		// when
		final TaskEntity actual = subject.convert(dto);

		// then
		assertThat(actual).isEqualTo(entity);
	}

	@Test
	void should_convert_empty_dto_to_entity() {

		// when
		final TaskEntity actual = subject.convert(TaskDtoBuilder.builder().build());

		// then
		assertThat(actual).hasAllNullFieldsOrPropertiesExcept("audit");
	}

	@Test
	void should_convert_null_dto_to_entity() {

		// when
		final TaskEntity actual = subject.convert(null);

		// then
		assertThat(actual).isNull();
	}

	@Test
	void should_convert_full_entity_to_dto() {

		// when
		final TaskDto actual = subject.reverse().convert(entity);

		// then
		assertThat(actual).isEqualTo(dto);
	}

	@Test
	void should_convert_empty_entity_to_dto() {

		// when
		final TaskDto actual = subject.reverse().convert(TaskEntityBuilder.builder().build());

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
	}

	@Test
	void should_convert_null_entity_to_dto() {

		// when
		final TaskDto actual = subject.reverse().convert(null);

		// then
		assertThat(actual).isNull();
	}
}
