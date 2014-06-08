package ${package}.components;

import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.componentsinfo.HealthCheckInfo;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = HealthCheckInfo.class)
public class HealthCheck extends AjaxEnabledComponent<String> {

	@Override
	public String getModel(HstRequest request, HstResponse response) throws HstComponentException {
		return this.<HealthCheckInfo>getComponentParametersInfo(request).getMessage();
	}

}
