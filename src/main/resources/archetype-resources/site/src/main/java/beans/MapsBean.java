package ${package}.beans;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ${package}.componentsinfo.catalog.MapsInfo;

public class MapsBean {
	
	private static final char MARKER_LABEL = 'A';
	
	private String apiKey;
	private String client;

	private boolean sensor = false;
	private MapType mapType;
	private Double latitude;
	private Double longitude;
	private String address;
	private int zoom = 8;
	private int height = 300;
	private String markerTitle;
	private String markerColor;

	private boolean showStaticsMap = true;
	private String signature;
	private int width = 300;

	private boolean showDynamicMap = true;
	private Character markerLabel;
	private boolean disableDefaultUI = false;
	private boolean overviewMapControl = true;
	private boolean panControl = true;
	private int tilt = 0; 
	private boolean rotateControl = true;
	private boolean scaleControl = true;
	private boolean scrollwheel = true;
	private boolean streetViewControl = true;
	private boolean draggable = true;
	private boolean disableDoubleClickZoom = false;
	private String backgroundColor;

	public MapsBean(MapsInfo mapsInfo) {
		this.apiKey = mapsInfo.getApiKey();
		this.client = mapsInfo.getClient();

		this.sensor = mapsInfo.isSensor();
		this.mapType = MapType.valueOf(mapsInfo.getMapType().toUpperCase());
		this.latitude = mapsInfo.getLatitude();
		this.longitude = mapsInfo.getLongitude();
		this.address = mapsInfo.getAddress();
		this.zoom = mapsInfo.getZoom();
		this.height = mapsInfo.getHeight();
		this.markerTitle = mapsInfo.getMarkerTitle();
		this.markerColor = mapsInfo.getMarkerColor();

		this.showStaticsMap = mapsInfo.isShowStaticsMap();
		this.signature = mapsInfo.getSignature();
		this.width = mapsInfo.getWidth();

		this.showDynamicMap = mapsInfo.isShowDynamicMap();
		this.markerLabel = mapsInfo.getMarkerLabel();
		this.disableDefaultUI = mapsInfo.isDisableDefaultUI();
		this.overviewMapControl = mapsInfo.isOverviewMapControl();
		this.panControl = mapsInfo.isPanControl();
		if (StringUtils.isNumeric(mapsInfo.getTilt())) {
			this.tilt = Integer.parseInt(mapsInfo.getTilt());
		}
		this.rotateControl = mapsInfo.isRotateControl();
		this.scaleControl = mapsInfo.isScaleControl();
		this.scrollwheel = mapsInfo.isScrollwheel();
		this.streetViewControl = mapsInfo.isStreetViewControl();
		this.draggable = mapsInfo.isDraggable();
		this.disableDoubleClickZoom = mapsInfo.isDisableDoubleClickZoom();
		this.backgroundColor = mapsInfo.getBackgroundColor();
	}
	
	
	public MapsBean(Map<String, Object> mixinParams) {
		this.apiKey = (String) mixinParams.get(MapsInfo.FIELD_API_KEY);
		this.client = (String) mixinParams.get(MapsInfo.FIELD_CLIENT);	
		this.sensor = (Boolean) mixinParams.get(MapsInfo.FIELD_SENSOR);
		this.mapType = MapType.valueOf(((String) mixinParams.get(MapsInfo.FIELD_MAP_TYPE)).toUpperCase());
		
		if (!StringUtils.isEmpty((String) mixinParams.get(MapsInfo.FIELD_LATITUDE))) {
			this.latitude = (Double) mixinParams.get(MapsInfo.FIELD_LATITUDE);
		}
		
		if (!StringUtils.isEmpty((String) mixinParams.get(MapsInfo.FIELD_LONGITUDE))) {
			this.latitude = (Double) mixinParams.get(MapsInfo.FIELD_LONGITUDE);
		}

		this.address = (String) mixinParams.get(MapsInfo.FIELD_ADDRESS);
		this.zoom = ((Long) mixinParams.get(MapsInfo.FIELD_ZOOM)).intValue();
		this.height = ((Long) mixinParams.get(MapsInfo.FIELD_HEIGHT)).intValue();
		this.markerTitle = (String) mixinParams.get(MapsInfo.FIELD_MARKER_TITLE);
		this.markerColor = (String) mixinParams.get(MapsInfo.FIELD_MARKER_COLOR);
		
		this.showStaticsMap = (Boolean) mixinParams.get(MapsInfo.FIELD_SHOW_STATICS_MAP);
		this.signature = (String) mixinParams.get(MapsInfo.FIELD_SIGNATURE);
		this.width = ((Long) mixinParams.get(MapsInfo.FIELD_WIDTH)).intValue();
		
		this.showDynamicMap = (Boolean) mixinParams.get(MapsInfo.FIELD_SHOW_DYNAMIC_MAP);
		this.markerLabel = ((String) mixinParams.get(MapsInfo.FIELD_MARKER_LABEL)).isEmpty()
				?MARKER_LABEL:((String) mixinParams.get(MapsInfo.FIELD_MARKER_LABEL)).charAt(0);
		this.disableDefaultUI = (Boolean) mixinParams.get(MapsInfo.FIELD_DISABLE_DEFAULT_UI);
		this.overviewMapControl = (Boolean) mixinParams.get(MapsInfo.FIELD_OVERVIEW_MAP_CONTROL);
		this.panControl = (Boolean) mixinParams.get(MapsInfo.FIELD_PAN_CONTROL);
		if (StringUtils.isNumeric((String) mixinParams.get(MapsInfo.FIELD_TILT))) {
			this.tilt = Integer.parseInt((String) mixinParams.get(MapsInfo.FIELD_TILT));
		}
		
		this.rotateControl = (Boolean) mixinParams.get(MapsInfo.FIELD_ROTATE_CONTROL);
		this.scaleControl = (Boolean) mixinParams.get(MapsInfo.FIELD_SCALE_CONTROL);
		this.scrollwheel = (Boolean) mixinParams.get(MapsInfo.FIELD_SCROLL_WHEEL);
		this.streetViewControl = (Boolean) mixinParams.get(MapsInfo.FIELD_STREET_VIEW_CONTROL);
		this.draggable = (Boolean) mixinParams.get(MapsInfo.FIELD_DRAGGABLE);
		this.disableDoubleClickZoom = (Boolean) mixinParams.get(MapsInfo.FIELD_DISABLE_DOUBLE_CLICK_ZOOM);
		this.backgroundColor = (String) mixinParams.get(MapsInfo.FIELD_BACKGROUND_COLOR);
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getClient() {
		return client;
	}

	public String getSignature() {
		return signature;
	}

	public boolean isSensor() {
		return sensor;
	}

	public MapType getMapType() {
		return mapType;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getAddress() {
		return address;
	}

	public int getZoom() {
		return zoom;
	}

	public Character getMarkerLabel() {
		return markerLabel;
	}

	public String getMarkerTitle() {
		return markerTitle;
	}

	public boolean isDisableDefaultUI() {
		return disableDefaultUI;
	}

	public boolean isOverviewMapControl() {
		return overviewMapControl;
	}

	public boolean isPanControl() {
		return panControl;
	}

	public boolean isRotateControl() {
		return rotateControl;
	}

	public boolean isScaleControl() {
		return scaleControl;
	}

	public boolean isScrollwheel() {
		return scrollwheel;
	}

	public boolean isStreetViewControl() {
		return streetViewControl;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public boolean isDisableDoubleClickZoom() {
		return disableDoubleClickZoom;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isShowStaticsMap() {
		return showStaticsMap;
	}

	public boolean isShowDynamicMap() {
		return showDynamicMap;
	}

	public String getMarkerColor() {
		return markerColor;
	}
	
	public int getTilt() {
		return tilt;
	}
	
	enum MapType {

		ROADMAP("roadmap"), SATELLITE("satellite"), TERRAIN("terrain"), HYBRID(
				"hybrid");

		private String label;

		private MapType(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
}
