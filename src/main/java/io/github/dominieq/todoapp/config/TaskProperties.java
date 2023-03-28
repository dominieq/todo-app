package io.github.dominieq.todoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("task")
public class TaskProperties {

	private Template template;

	public Template getTemplate() {
		return template;
	}

	void setTemplate(final Template template) {
		this.template = template;
	}

	public static class Template {

		private boolean allowMultipleTasks;

		public boolean isAllowMultipleTasks() {
			return allowMultipleTasks;
		}

		public void setAllowMultipleTasks(final boolean allowMultipleTasks) {
			this.allowMultipleTasks = allowMultipleTasks;
		}
	}
}
