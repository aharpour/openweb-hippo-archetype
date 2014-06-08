package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.Color;
import org.hippoecm.hst.core.parameters.DropDownList;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
	@FieldGroup(titleKey = "mixin.group.title", value = { MapsInfo.FIELD_USER_MIXIN }),
	@FieldGroup(titleKey = "general.group.title", value = { MapsInfo.FIELD_ADDRESS, MapsInfo.FIELD_LATITUDE,
				MapsInfo.FIELD_LONGITUDE, MapsInfo.FIELD_HEIGHT, MapsInfo.FIELD_ZOOM, MapsInfo.FIELD_SENSOR,
				MapsInfo.FIELD_MAP_TYPE
				}),
	@FieldGroup(titleKey = "static.map.group.title", value = { MapsInfo.FIELD_SHOW_STATICS_MAP,
				MapsInfo.FIELD_CLIENT, MapsInfo.FIELD_SIGNATURE, MapsInfo.FIELD_WIDTH, MapsInfo.FIELD_MARKER_LABEL,
				MapsInfo.FIELD_MARKER_COLOR }),
	@FieldGroup(titleKey = "dynamic.group.title", value = { MapsInfo.FIELD_SHOW_DYNAMIC_MAP,
				MapsInfo.FIELD_API_KEY, MapsInfo.FIELD_DISABLE_DEFAULT_UI, MapsInfo.FIELD_OVERVIEW_MAP_CONTROL,
				MapsInfo.FIELD_PAN_CONTROL, MapsInfo.FIELD_ROTATE_CONTROL, MapsInfo.FIELD_SCALE_CONTROL,
				MapsInfo.FIELD_SCROLL_WHEEL, MapsInfo.FIELD_STREET_VIEW_CONTROL, MapsInfo.FIELD_DRAGGABLE,
				MapsInfo.FIELD_DISABLE_DOUBLE_CLICK_ZOOM, MapsInfo.FIELD_BACKGROUND_COLOR, MapsInfo.FIELD_MARKER_TITLE }) 
})
public interface MapsInfo {

	public static final String FIELD_USER_MIXIN = "useMixin";
	
	public static final String FIELD_ADDRESS = "address";
	public static final String FIELD_LATITUDE = "latitude";
	public static final String FIELD_LONGITUDE = "longitude";
	public static final String FIELD_HEIGHT = "height";
	public static final String FIELD_ZOOM = "zoom";
	public static final String FIELD_MARKER_LABEL = "markerLabel";
	public static final String FIELD_MARKER_COLOR = "markerColor";
	public static final String FIELD_SENSOR = "sensor";
	public static final String FIELD_USE_MIXIN = "useMixin";

	public static final String FIELD_SHOW_DYNAMIC_MAP = "showDynamicMap";
	public static final String FIELD_DISABLE_DEFAULT_UI = "disableDefaultUI";
	public static final String FIELD_OVERVIEW_MAP_CONTROL = "overviewMapControl";
	public static final String FIELD_PAN_CONTROL = "panControl";
	public static final String FIELD_ROTATE_CONTROL = "rotateControl";
	public static final String FIELD_SCALE_CONTROL = "scaleControl";
	public static final String FIELD_SCROLL_WHEEL = "scrollwheel";
	public static final String FIELD_STREET_VIEW_CONTROL = "streetViewControl";
	public static final String FIELD_DRAGGABLE = "draggable";
	public static final String FIELD_DISABLE_DOUBLE_CLICK_ZOOM = "disableDoubleClickZoom";
	public static final String FIELD_BACKGROUND_COLOR = "backgroundColor";
	public static final String FIELD_MARKER_TITLE = "markerTitle";
	public static final String FIELD_API_KEY = "apiKey";
	public static final String FIELD_CLIENT = "client";

	public static final String FIELD_SIGNATURE = "signature";
	public static final String FIELD_SHOW_STATICS_MAP = "showStaticsMap";
	public static final String FIELD_WIDTH = "width";
	public static final String FIELD_MAP_TYPE = "mapType";
	public static final String FIELD_TILT = "tilt";

	@Parameter(name = FIELD_API_KEY)
	public String getApiKey();

	@Parameter(name = FIELD_CLIENT)
	public String getClient();

	@Parameter(name = FIELD_SIGNATURE)
	public String getSignature();

	@Parameter(name = FIELD_SENSOR, defaultValue = "off")
	public boolean isSensor();

	@DropDownList({ "roadmap", "satellite", "terrain", "hybrid" })
	@Parameter(name = FIELD_MAP_TYPE, defaultValue = "roadmap")
	public String getMapType();

	@Parameter(name = FIELD_USE_MIXIN, defaultValue = "off")
	public boolean getUseMixin();

	@Parameter(name = FIELD_ADDRESS)
	public String getAddress();

	@Parameter(name = FIELD_LATITUDE)
	public Double getLatitude();

	@Parameter(name = FIELD_LONGITUDE)
	public Double getLongitude();

	@Parameter(name = FIELD_ZOOM, defaultValue = "13")
	public int getZoom();

	@Parameter(name = FIELD_HEIGHT, defaultValue = "640")
	public int getHeight();

	@Parameter(name = FIELD_MARKER_LABEL)
	public char getMarkerLabel();

	@Parameter(name = FIELD_SHOW_STATICS_MAP, defaultValue = "on")
	public boolean isShowStaticsMap();

	@Parameter(name = FIELD_SHOW_DYNAMIC_MAP, defaultValue = "on")
	public boolean isShowDynamicMap();

	@Parameter(name = FIELD_MARKER_TITLE)
	public String getMarkerTitle();

	@Parameter(name = FIELD_DISABLE_DEFAULT_UI, defaultValue = "off")
	public boolean isDisableDefaultUI();

	@Parameter(name = FIELD_OVERVIEW_MAP_CONTROL, defaultValue = "on")
	public boolean isOverviewMapControl();

	@Parameter(name = FIELD_PAN_CONTROL, defaultValue = "on")
	public boolean isPanControl();

	@DropDownList({ "0", "45" })
	@Parameter(name = FIELD_TILT, defaultValue = "0")
	public String getTilt();

	@Parameter(name = FIELD_ROTATE_CONTROL, defaultValue = "on")
	public boolean isRotateControl();

	@Parameter(name = FIELD_SCALE_CONTROL, defaultValue = "on")
	public boolean isScaleControl();

	@Parameter(name = FIELD_SCROLL_WHEEL, defaultValue = "on")
	public boolean isScrollwheel();

	@Parameter(name = FIELD_STREET_VIEW_CONTROL, defaultValue = "on")
	public boolean isStreetViewControl();

	@Parameter(name = FIELD_DRAGGABLE, defaultValue = "on")
	public boolean isDraggable();

	@Parameter(name = FIELD_DISABLE_DOUBLE_CLICK_ZOOM, defaultValue = "off")
	public boolean isDisableDoubleClickZoom();

	@Color
	@Parameter(name = FIELD_BACKGROUND_COLOR)
	public String getBackgroundColor();

	@Parameter(name = FIELD_WIDTH, defaultValue = "640")
	public int getWidth();

	@DropDownList({ "red", "blue", "green", "yellow" })
	@Parameter(name = FIELD_MARKER_COLOR, defaultValue = "red")
	public String getMarkerColor();

}
