#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.compounds;

import generated.beans.LatestItemsIntroParameters;
import generated.beans.LatestItemsOverviewParameters;
import generated.beans.LatestItemsSortSizeParameters;
import generated.beans.LatestItemsTitleParameters;

import java.util.HashMap;
import java.util.Map;

import org.hippoecm.hst.content.beans.Node;

import ${package}.componentsinfo.catalog.LatestItemsInfo;

@Node(jcrType = "${namespace}:LatestItemsCompoundMixin")
public class LatestItemsCompoundMixin extends generated.beans.LatestItemsCompoundMixin {

	private LatestItemsParemeters latestItemsParemeters;
	
	public Map<String, Object> getConfigObject() {
		Map<String, Object> parametersInfoMap = new HashMap<String, Object>(2);
		
		LatestItemsTitleParameters titleParams = getLatestItemsTitleParameters();
		parametersInfoMap.put(LatestItemsInfo.WIDGET_TITLE, titleParams.getWidgetTitle());
		parametersInfoMap.put(LatestItemsInfo.TITLE_BACKGROUND_COLOR, titleParams.getTitleBackgroundColor());
		parametersInfoMap.put(LatestItemsInfo.TITLE_FONT_COLOR, titleParams.getTitleFontColor());
		
		LatestItemsSortSizeParameters sortParams = getLatestItemsSortSizeParameters();
		parametersInfoMap.put(LatestItemsInfo.SIZE, sortParams.getSize());
		parametersInfoMap.put(LatestItemsInfo.SORT_BY, sortParams.getSortBy().getFirstItem().getLabel());
		parametersInfoMap.put(LatestItemsInfo.SORT_ORDER, sortParams.getSortOrder().getFirstItem().getLabel());
		
		LatestItemsParemeters itemsParams = getLatestItemsParemeters();
		parametersInfoMap.put(LatestItemsInfo.CONTENT_BEAN_PATH, itemsParams.getContentBeanPath().getLocalizedName());
		parametersInfoMap.put(LatestItemsInfo.SHOW_TYPE, itemsParams.getShowType().getFirstItem().getLabel());
	
		LatestItemsIntroParameters introParams = getLatestItemsIntroParameters();
		parametersInfoMap.put(LatestItemsInfo.MAX_LENGTH, introParams.getMaxLength());
		parametersInfoMap.put(LatestItemsInfo.CUTTING_TOLERANCE, introParams.getCuttingTolerance());
		
		LatestItemsOverviewParameters overviewParams = getLatestItemsOverviewParameters();
		parametersInfoMap.put(LatestItemsInfo.OVERVIEW_BEAN_PATH, overviewParams.getOverviewBeanPath());
		parametersInfoMap.put(LatestItemsInfo.SHOW_OVERVIEW_LINK, overviewParams.getShowOverviewLink());
		parametersInfoMap.put(LatestItemsInfo.OVERVIEW_LINK_LABEL, overviewParams.getOverviewLinkLabel());
		
		return parametersInfoMap;
	}
	
	@Override
	public LatestItemsParemeters getLatestItemsParemeters() {
		if (this.latestItemsParemeters == null) {
			this.latestItemsParemeters = getBean("${namespace}:latestItemsParemeters", LatestItemsParemeters.class);
		}
		return this.latestItemsParemeters;
	}
}
