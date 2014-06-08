#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.compounds;

import generated.beans.RssConfigurationParameters;
import generated.beans.RssConnectionParameters;

import java.util.HashMap;
import java.util.Map;

import org.hippoecm.hst.content.beans.Node;

import ${package}.componentsinfo.catalog.RssFeedInfo;

@Node(jcrType = "${namespace}:RssCompoundMixin")
public class RssCompoundMixin extends generated.beans.RssCompoundMixin {
	
	public Map<String, Object> getConfigObject() {
		Map<String, Object> parametersInfoMap = new HashMap<String, Object>(2);
		
		RssConfigurationParameters configParams = getRssConfigurationParameters();
		parametersInfoMap.put(RssFeedInfo.FEED_URL, configParams.getFeedUrl());
		parametersInfoMap.put(RssFeedInfo.NUMBER_OF_ITEMS, configParams.getNumberOfItems());
		parametersInfoMap.put(RssFeedInfo.TITLE, configParams.getTitle());

		RssConnectionParameters connectParams = getRssConnectionParameters();
		parametersInfoMap.put(RssFeedInfo.CONNECT_TIMEOUT, connectParams.getConnectTimeout());
		parametersInfoMap.put(RssFeedInfo.READ_TIMEOUT, connectParams.getReadTimeout());
		parametersInfoMap.put(RssFeedInfo.UPDATE_INTERVAL, connectParams.getUpdateInterval());

		return parametersInfoMap;
	}
	
}
