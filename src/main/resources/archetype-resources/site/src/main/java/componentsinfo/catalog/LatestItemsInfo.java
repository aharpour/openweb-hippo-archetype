#set( $symbol_dollar = '$' )
package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.Color;
import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.DropDownList;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.JcrPath;
import org.hippoecm.hst.core.parameters.Parameter;

import ${package}.utils.Constants.DisplayedFieldNames;
import ${package}.utils.Constants.DisplayedNodeTypes;
import com.tdclighthouse.prototype.componentsinfo.ContentBeanPathInfo;
import com.tdclighthouse.prototype.utils.Constants;

@FieldGroupList({
	@FieldGroup(titleKey = "mixin.group.title", value = { LatestItemsInfo.FIELD_USER_MIXIN }),
	@FieldGroup(titleKey = "widget.group.title", value = { LatestItemsInfo.WIDGET_TITLE, LatestItemsInfo.TITLE_BACKGROUND_COLOR, 
			LatestItemsInfo.TITLE_FONT_COLOR }),
	@FieldGroup(titleKey = "items.group.title", value = {LatestItemsInfo.CONTENT_BEAN_PATH, LatestItemsInfo.SHOW_TYPE }),
	@FieldGroup(titleKey = "items.sort.title", value = {LatestItemsInfo.SIZE, LatestItemsInfo.SORT_BY, LatestItemsInfo.SORT_ORDER}),
	@FieldGroup(titleKey = "intro.group.title", value = {LatestItemsInfo.MAX_LENGTH, LatestItemsInfo.CUTTING_TOLERANCE}),
	@FieldGroup(titleKey = "overview.group.title", value = { LatestItemsInfo.SHOW_OVERVIEW_LINK, LatestItemsInfo.OVERVIEW_BEAN_PATH, 
			LatestItemsInfo.OVERVIEW_LINK_LABEL }) 
})
public interface LatestItemsInfo extends ContentBeanPathInfo {

	public static final String FIELD_USER_MIXIN = "useMixin";
	
	public static final String WIDGET_TITLE = "widgetTitle";
	public static final String WIDGET_TITLE_DEFAULT = "Latest Items";
	public static final String TITLE_BACKGROUND_COLOR = "titleBackgroundColor";
	public static final String TITLE_BACKGROUND_COLOR_DEFAULT = "#C0C0C0";
	public static final String TITLE_FONT_COLOR = "titleFontColor";
	public static final String TITLE_FONT_COLOR_DEFAULT = "#000000";
	
	public static final String SHOW_OVERVIEW_LINK = "showOverviewLink";
	public static final String OVERVIEW_BEAN_PATH = "overviewBeanPath";
	public static final String OVERVIEW_BEAN_PATH_DOC_TYPE = "tdc:WebDocument";
	public static final String INITIAL_LOCATION = "pages";
	public static final String OVERVIEW_LINK_LABEL = "overviewLinkLabel";
	
	public static final String CONTENT_BEAN_PATH = "contentBeanPath";
	public static final String CONTENT_BEAN_PATH_SELECTABLE = "hippostd:folder";
	public static final String SHOW_TYPE = "showType";
	public static final String SORT_ORDER = "sortOrder";
	public static final String SORT_BY = "sortBy";
	public static final String SIZE = "size";
	
	public static final String MAX_LENGTH = "maxLength";
	public static final String CUTTING_TOLERANCE="cuttingTolerance";
	
	@Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();
	
	@Parameter(name = WIDGET_TITLE, defaultValue = WIDGET_TITLE_DEFAULT)
	public String getWidgetTitle();
	
	@Color
	@Parameter(name = TITLE_BACKGROUND_COLOR, defaultValue = TITLE_BACKGROUND_COLOR_DEFAULT, required = false)
	public String getTitleBackgroundColor();
	
	@Color
	@Parameter(name = TITLE_FONT_COLOR, defaultValue = TITLE_FONT_COLOR_DEFAULT, required = false)
	public String getTitleFontColor();
	
	@Parameter(name = SHOW_OVERVIEW_LINK, defaultValue = "on")
	public boolean getShowOverviewLink();
	
	@Parameter(name = OVERVIEW_BEAN_PATH, defaultValue = "")
	@DocumentLink(docType = OVERVIEW_BEAN_PATH_DOC_TYPE, docLocation = INITIAL_LOCATION)
	public String getOverviewBeanPath();
	 
	@Parameter(name = OVERVIEW_LINK_LABEL, defaultValue = "")
	public String getOverviewLinkLabel();
	
	@Override
	@Parameter(name = CONTENT_BEAN_PATH, required = true)
	@JcrPath(isRelative = true, pickerInitialPath = INITIAL_LOCATION, pickerSelectableNodeTypes = {CONTENT_BEAN_PATH_SELECTABLE})
	public String getContentBeanPath();
	
	//TODO: change
	@Parameter(name = SHOW_TYPE, defaultValue = "WebDocument", required = true)
	@DropDownList(value = {DisplayedNodeTypes.TEXT_PAGE, DisplayedNodeTypes.ARTICLE_PAGE, DisplayedNodeTypes.GENERIC_PAGE} )
	public String getShowType();
	
	@Parameter(name = SORT_ORDER, defaultValue = Constants.Values.DESCENDING)
	@DropDownList(value = { Constants.Values.DESCENDING, Constants.Values.ASCENDING })
	public String getSortOrder();

	//TODO: change
	@Parameter(name = SORT_BY, defaultValue = "ReleaseDate")
	@DropDownList(value = {DisplayedFieldNames.TDC_RELEASE_DATE, DisplayedFieldNames.TDC_TITLE})
	public String getSortBy();
	
	@Parameter(name = SIZE, defaultValue = "2")
	public int getSize();
	
	@Parameter(name = MAX_LENGTH, defaultValue = "150")
	public int getMaxLength();
		
	@Parameter(name = CUTTING_TOLERANCE, defaultValue = "5")
	public int getCuttingTolerance();
}
