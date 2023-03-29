package io.github.dominieq.todoapp.database.entity;

import java.time.LocalDateTime;

public interface Audit {

	LocalDateTime getCreatedOn();

	LocalDateTime getUpdatedOn();
}
