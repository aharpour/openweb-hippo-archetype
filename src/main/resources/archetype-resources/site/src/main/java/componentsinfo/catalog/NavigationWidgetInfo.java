package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

import com.tdclighthouse.prototype.componentsinfo.NavigationInfo;

@FieldGroupList({
	@FieldGroup(titleKey = "navigation.customization.group", value = { NavigationWidgetInfo.DEPTH, 
			NavigationWidgetInfo.RECURSE_ONLY_EXPANDED}) 
})
public interface NavigationWidgetInfo extends NavigationInfo{

	public static final String DEPTH = "depth";
	public static final String RECURSE_ONLY_EXPANDED = "recurseOnlyExpanded";

	@Parameter(name = DEPTH, defaultValue="5")
	public int getDepth();

	@Parameter(name = RECURSE_ONLY_EXPANDED, defaultValue = "off")
	public boolean getRecurseOnlyExpanded();
	
	

}
