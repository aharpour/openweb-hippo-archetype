#set( $symbol_dollar = '$' )
package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
	@FieldGroup(titleKey = "mixin.group.title", value = { RssFeedInfo.FIELD_USER_MIXIN }),
	@FieldGroup(titleKey = "rssfeed.title.group", value = { RssFeedInfo.TITLE}),
	@FieldGroup(titleKey = "rssfeed.url.group", value = { RssFeedInfo.FEED_URL, RssFeedInfo.NUMBER_OF_ITEMS}),
	@FieldGroup(titleKey = "rssfeed.connection.group", value = { RssFeedInfo.UPDATE_INTERVAL, 
			RssFeedInfo.CONNECT_TIMEOUT, RssFeedInfo.READ_TIMEOUT})
})
public interface RssFeedInfo {
	public static final String TITLE = "title";
    public static final String FEED_URL = "feedUrl";
    public static final String NUMBER_OF_ITEMS = "numberOfItems";
    public static final String UPDATE_INTERVAL = "updateInterval";
    public static final String CONNECT_TIMEOUT = "connectTimeout";
    public static final String READ_TIMEOUT = "readTimeout";
    public static final String FIELD_USER_MIXIN = "useMixin";

    @Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();
    
    @Parameter(name = TITLE, required = false, defaultValue = "Twitter")
    public String getTitle();

    @Parameter(name = FEED_URL, required = true, defaultValue = "")
    public String getFeedUrl();

    @Parameter(name = NUMBER_OF_ITEMS, required = true, defaultValue = "3")
    public int getNumberOfItems();

    @Parameter(name = UPDATE_INTERVAL, required = true, defaultValue = "15")
    public int getUpdateInterval();

    @Parameter(name = CONNECT_TIMEOUT, required = true, defaultValue = "2000")
    public int getConnectTimeout();

    @Parameter(name = READ_TIMEOUT, required = true, defaultValue = "5000")
    public int getReadTimeout();

}
