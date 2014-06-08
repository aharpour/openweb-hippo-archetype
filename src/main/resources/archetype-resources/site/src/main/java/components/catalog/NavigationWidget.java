package ${package}.components.catalog;

import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.componentsinfo.catalog.NavigationWidgetInfo;
import com.tdclighthouse.prototype.components.Navigation;

@ParametersInfo(type = NavigationWidgetInfo.class)
public class NavigationWidget extends Navigation {
	
	@Override
	public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
		super.doBeforeRender(request, response);
		
		NavigationWidgetInfo parametersInfo = this.<NavigationWidgetInfo> getComponentParametersInfo(request);
		request.setAttribute(NavigationWidgetInfo.DEPTH, parametersInfo.getDepth());
		request.setAttribute(NavigationWidgetInfo.RECURSE_ONLY_EXPANDED, parametersInfo.getRecurseOnlyExpanded());
	}
}
