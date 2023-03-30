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
	void should_build_from_different_full_dto() {

		// given
		final TaskGroupDto other = new TaskGroupDto(
				1, "Builder Test", true, LocalDateTime.parse("2023-03-30T13:15:00"), Set.of());

		// when
		final TaskGroupDto actual =  subject
				.from(other)
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
	void should_build_from_different_empty_dto() {

		// given
		final TaskGroupDto other = new TaskGroupDto(null, null, null, null, null);

		// when
		final TaskGroupDto actual =  subject
				.from(other)
				.build();

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
	}

	@Test
	void should_not_build_from_different_null_dto() {

		// when
		final TaskGroupDto actual =  subject
				.from(null)
				.build();

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
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
