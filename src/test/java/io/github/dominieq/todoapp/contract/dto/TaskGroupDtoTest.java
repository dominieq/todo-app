package io.github.dominieq.todoapp.contract.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.dominieq.todoapp.contract.dto.builder.TaskDtoBuilder;
import io.github.dominieq.todoapp.contract.dto.builder.TaskGroupDtoBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TaskGroupDtoTest {

	private static ValidatorFactory validatorFactory;
	private ObjectMapper objectMapper;
	private Validator validator;

	@BeforeAll
	static void initialize() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
	}

	@AfterAll
	static void cleanUp() {
		validatorFactory.close();
	}

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		validator = validatorFactory.getValidator();
	}

	@Test
	void should_not_return_constraint_violations() {

		// given
		final TaskGroupDto dto = TaskGroupDtoBuilder.builder()
				.withDescription("A")
				.withTasks(Set.of(
						TaskDtoBuilder.builder()
								.withDescription("A")
								.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
								.build()))
				.build();

		// when
		final Set<ConstraintViolation<TaskGroupDto>> actual = validator.validate(dto);

		// then
		assertThat(actual).isEmpty();
	}

	@ParameterizedTest
	@MethodSource("invalid_dto_without_cascading_source")
	void should_return_constraint_violations_without_cascading(final Set<TaskDto> tasks) {

		// given
		final TaskGroupDto dto = TaskGroupDtoBuilder.builder()
				.withId(1)
				.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
				.withDone(true)
				.withTasks(tasks)
				.build();

		// when
		final Set<ConstraintViolation<TaskGroupDto>> actual = validator.validate(dto);

		// then
		assertThat(actual).hasSize(5);

		final List<String> messages = actual.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());

		assertThat(messages).containsExactlyInAnyOrder(
				"TaskGroup.Id should be 'null'.",
				"TaskGroup.Description shouldn't be empty.",
				"TaskGroup.Done should be 'null'.",
				"TaskGroup.Deadline should be 'null'.",
				"TaskGroup.Tasks shouldn't be empty.");
	}

	private static Stream<Arguments> invalid_dto_without_cascading_source() {
		return Stream.of(
				Arguments.of((Set<TaskGroupDto>) null),
				Arguments.of(Set.of()));
	}

	@Test
	void should_return_constraint_violation_with_cascading() {

		// given
		final TaskGroupDto dto = TaskGroupDtoBuilder.builder()
				.withId(1)
				.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
				.withDone(true)
				.withTasks(Set.of(
						TaskDtoBuilder.builder()
								.withId(1)
								.withDone(true)
								.build()
				))
				.build();

		// when
		final Set<ConstraintViolation<TaskGroupDto>> actual = validator.validate(dto);

		// then
		assertThat(actual).hasSize(8);

		final List<String> messages = actual.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());

		assertThat(messages).containsExactlyInAnyOrder(
				"TaskGroup.Id should be 'null'.",
				"TaskGroup.Description shouldn't be empty.",
				"TaskGroup.Done should be 'null'.",
				"TaskGroup.Deadline should be 'null'.",
				"Task.Id shouldn't exist.",
				"Task.Description shouldn't be empty.",
				"Task.Done shouldn't exist.",
				"Task.Deadline shouldn't be null.");
	}

	@Test
	void should_deserialize_full_dto()
			throws Exception {

		// given
		final String file = readFile("_dto/TaskGroupDtoFull.json");

		// when
		final TaskGroupDto actual = objectMapper.readValue(file, TaskGroupDto.class);

		// then
		final TaskGroupDto expected = TaskGroupDtoBuilder.builder()
				.withId(1)
				.withDescription("Dto Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
				.withTasks(Set.of(
						TaskDtoBuilder.builder()
								.withId(1)
								.withDescription("Dto Test")
								.withDone(true)
								.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
								.build()))
				.build();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void should_deserialize_empty_dto()
			throws Exception {

		// when
		final TaskGroupDto actual = objectMapper.readValue("{}", TaskGroupDto.class);

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
	}

	@Test
	void should_serialize_full_dto()
			throws Exception {

		// given
		final TaskGroupDto dto = TaskGroupDtoBuilder.builder()
				.withId(1)
				.withDescription("Dto Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
				.withTasks(Set.of(
						TaskDtoBuilder.builder()
								.withId(1)
								.withDescription("Dto Test")
								.withDone(true)
								.withDeadline(LocalDateTime.parse("2023-03-30T17:00:00"))
								.build()))
				.build();

		// when
		final String actual = objectMapper.writeValueAsString(dto);

		// then
		final String expected = readFile("_dto/TaskGroupDtoFull.json");
		assertThat(actual).isEqualToIgnoringWhitespace(expected);
	}

	@Test
	void should_serialize_empty_dto()
			throws Exception {

		// when
		final String actual = objectMapper.writeValueAsString(TaskGroupDtoBuilder.builder().build());

		// then
		final String expected = readFile("_dto/TaskGroupDtoEmpty.json");
		assertThat(actual).isEqualToIgnoringWhitespace(expected);
	}

	private String readFile(final String filename) {
		try {
			final InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
			if (stream == null) throw new IllegalArgumentException("File doesn't exist.");

			return new String(stream.readAllBytes());
		} catch (IOException exception) {
			throw new UncheckedIOException(exception);
		}
	}
}
