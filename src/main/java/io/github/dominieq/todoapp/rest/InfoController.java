package io.github.dominieq.todoapp.rest;

import io.github.dominieq.todoapp.config.TaskProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController {

	private final DataSourceProperties dataSourceProps;
	private final TaskProperties taskProps;

	InfoController(final DataSourceProperties dataSourceProps,
				   final TaskProperties taskProps) {

		this.dataSourceProps = dataSourceProps;
		this.taskProps = taskProps;
	}

	@GetMapping("/info/url")
	String getUrl() {
		return dataSourceProps.getUrl();
	}

	@GetMapping("/info/prop")
	boolean getMyProp() {
		return taskProps.isAllowMultipleTasksFromTemplate();
	}
}
