package mesosphere.marathon.client.model.v2;

/**
 * Enumerates the additional resources that clients may request Marathon to include in response pertaining to
 * applications.
 */
public enum EmbeddedAppResource {
	APP_TASKS("apps.tasks"),
	APP_COUNTS("apps.counts"),
	APP_DEPLOYMENTS("apps.deployments"),
	APP_LAST_TASK_FAILURES("apps.lastTaskFailures"),
	APP_FAILURES("apps.failures"),
	APP_TASK_STATS("apps.taskStats");

	private final String parameterValue;

	EmbeddedAppResource(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	public String toString() {
		return parameterValue;
	}
}
