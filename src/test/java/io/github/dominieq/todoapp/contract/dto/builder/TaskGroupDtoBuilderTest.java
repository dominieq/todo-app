package io.github.dominieq.todoapp.contract.dto.builder;

import io.github.dominieq.todoapp.contract.dto.TaskGroupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TaskGroupDtoBuilderTest {

	private TaskGroupDtoBuilder subject;

	@BeforeEach
	void setUp() {
		subject = TaskGroupDtoBuilder.builder();
	}

	@Test
	void should_build_dto_with_all_fields() {

		// when
		final TaskGroupDto actual = subject
				.withId(1)
				.withDescription("Builder Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T13:15:00"))
				.withTasks(Set.of())
				.build();

		// then
		assertThat(actual)
				.hasFieldOrPropertyWithValue("id", 1)
				.hasFieldOrPropertyWithValue("description", "Builder Test")
				.hasFieldOrPropertyWithValue("done", true)
				.hasFieldOrPropertyWithValue("deadline", LocalDateTime.parse("2023-03-30T13:15:00"))
				.hasFieldOrPropertyWithValue("tasks", Set.of());
	}

	@Test
	void should_build_empty_dto() {

		// when
		final TaskGroupDto actual = subject.build();

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
	}
}
