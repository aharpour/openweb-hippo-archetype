package ${package}.componentsinfo;

import org.hippoecm.hst.core.parameters.Parameter;

public interface HealthCheckInfo {

	@Parameter(name = "message", displayName = "Message to be display on the health check page", required = true)
	public String getMessage();

}
