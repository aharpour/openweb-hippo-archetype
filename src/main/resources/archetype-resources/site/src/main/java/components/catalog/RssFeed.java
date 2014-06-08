/**
 * Copyright 2010-2013 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * modified by Smile Benelux (http://smile-benelux.com/)
 */
#set( $symbol_dollar = '$' )
package ${package}.components.catalog;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import ${package}.beans.mixins.RssMixin;
import ${package}.componentsinfo.catalog.RssFeedInfo;
import ${package}.utils.Constants.Attributes;
import ${package}.utils.FeedFetcher;
import com.sun.syndication.feed.synd.SyndFeed;
import com.tdclighthouse.prototype.components.AjaxEnabledComponent;

@ParametersInfo(type = RssFeedInfo.class)
public class RssFeed extends AjaxEnabledComponent<Map<String, Object>> {

	private static final FeedFetcher feedFetcher = new FeedFetcher();

	public Map<String, Object> getModel(HstRequest request, HstResponse response) {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			RssFeedInfo info = this.<RssFeedInfo> getComponentParametersInfo(request);
			if (info.getUseMixin()) {
				populateModelFromMixin(request, model);
			} else {
				populateModelFromParametrs(model, info);
			}
			return model;
		} catch (RepositoryException e) {
			throw new HstComponentException(e.getMessage(), e);
		}
	}

	private void populateModelFromParametrs(Map<String, Object> model, RssFeedInfo info) {
		model.put("info", info);
		SyndFeed feed = feedFetcher.retrieveFeed(info.getFeedUrl(), info.getUpdateInterval(), 
				info.getConnectTimeout(), info.getReadTimeout());
		model.put("feed", feed);
	}

	private void populateModelFromMixin(HstRequest request, Map<String, Object> model) throws RepositoryException {
		Map<String, Object> infoMap;
		HippoBean proxy = getMixinProxy(getContentBean(request));
		if (proxy instanceof RssMixin) {
			infoMap = ((RssMixin) proxy).getRssCompoundMixin().getConfigObject();
			model.put("info", infoMap);
			SyndFeed feed = feedFetcher.retrieveFeed((String) infoMap.get(RssFeedInfo.FEED_URL), 
					((Long) infoMap.get(RssFeedInfo.UPDATE_INTERVAL)).intValue(), 
					((Long) infoMap.get(RssFeedInfo.CONNECT_TIMEOUT)).intValue(), 
					((Long) infoMap.get(RssFeedInfo.READ_TIMEOUT)).intValue());
			model.put("feed", feed);	
		} else {
			model.put(Attributes.WEBMASTER_ERROR_MESSAGE, Attributes.MIXIN_ERROR_MESSAGE);
		}
	}
}
