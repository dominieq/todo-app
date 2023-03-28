package io.github.dominieq.todoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("task")
public class TaskProperties {

	private boolean allowMultipleTasksFromTemplate;

	public boolean isAllowMultipleTasksFromTemplate() {
		return allowMultipleTasksFromTemplate;
	}

	void setAllowMultipleTasksFromTemplate(final boolean allowMultipleTasksFromTemplate) {
		this.allowMultipleTasksFromTemplate = allowMultipleTasksFromTemplate;
	}
}
