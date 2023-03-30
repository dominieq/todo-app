package io.github.dominieq.todoapp.contract.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.dominieq.todoapp.contract.dto.builder.TaskDtoBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

import static org.assertj.core.api.Assertions.assertThat;

class TaskDtoTest {

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
		final TaskDto dto = TaskDtoBuilder.builder()
				.withDescription("A")
				.withDeadline(LocalDateTime.parse("2023-03-30T16:00:00"))
				.build();

		// when
		final Set<ConstraintViolation<TaskDto>> actual = validator.validate(dto);

		// then
		assertThat(actual).isEmpty();
	}

	@Test
	void should_return_constraint_violations() {

		// given
		final TaskDto dto = TaskDtoBuilder.builder()
				.withId(1)
				.withDone(true)
				.build();

		// when
		final Set<ConstraintViolation<TaskDto>> actual = validator.validate(dto);

		// then
		assertThat(actual).hasSize(4);

		final List<String> messages = actual.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());

		assertThat(messages).containsExactlyInAnyOrder(
				"Task.Id shouldn't exist.",
				"Task.Description shouldn't be empty.",
				"Task.Done shouldn't exist.",
				"Task.Deadline shouldn't be null.");
	}

	@Test
	void should_deserialize_full_dto()
			throws Exception {

		// given
		final String file = readFile("_dto/TaskDtoFull.json");

		// when
		final TaskDto actual = objectMapper.readValue(file, TaskDto.class);

		// then
		final TaskDto expected = TaskDtoBuilder.builder()
				.withId(1)
				.withDescription("Dto Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T13:15:00"))
				.build();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void should_deserialize_empty_dto()
			throws Exception {

		// when
		final TaskDto actual = objectMapper.readValue("{}", TaskDto.class);

		// then
		assertThat(actual).hasAllNullFieldsOrProperties();
	}

	@Test
	void should_serialize_full_dto()
			throws Exception {

		// given
		final TaskDto dto = TaskDtoBuilder.builder()
				.withId(1)
				.withDescription("Dto Test")
				.withDone(true)
				.withDeadline(LocalDateTime.parse("2023-03-30T13:15:00"))
				.build();

		// when
		final String actual = objectMapper.writeValueAsString(dto);

		// then
		final String expected = readFile("_dto/TaskDtoFull.json");
		assertThat(actual).isEqualToIgnoringWhitespace(expected);
	}

	@Test
	void should_serialize_empty_dto()
			throws Exception {

		// when
		final String actual = objectMapper.writeValueAsString(TaskDtoBuilder.builder().build());

		// then
		final String expected = readFile("_dto/TaskDtoEmpty.json");
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
