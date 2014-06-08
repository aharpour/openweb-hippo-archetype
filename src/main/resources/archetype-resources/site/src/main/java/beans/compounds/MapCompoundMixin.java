#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.compounds;

import generated.beans.MapDynamicParameters;
import generated.beans.MapDynamicParametersSec;
import generated.beans.MapGeneralParameters;
import generated.beans.MapStaticParameters;

import java.util.HashMap;
import java.util.Map;

import org.hippoecm.hst.content.beans.Node;

import ${package}.componentsinfo.catalog.MapsInfo;

@Node(jcrType = "${namespace}:MapCompoundMixin")
public class MapCompoundMixin extends generated.beans.MapCompoundMixin{

	public Map<String, Object> getConfigObject() {
		Map<String, Object> infoMap = new HashMap<String, Object>(2);
		infoMap.put(MapsInfo.FIELD_ADDRESS, getAddress());
		
		MapGeneralParameters mapGeneralParams =  getMapGeneralParameters();
		infoMap.put(MapsInfo.FIELD_HEIGHT, mapGeneralParams.getHeight());
		infoMap.put(MapsInfo.FIELD_LATITUDE, mapGeneralParams.getLatitude()!=0.0?mapGeneralParams.getLatitude():"");
		infoMap.put(MapsInfo.FIELD_LONGITUDE, mapGeneralParams.getLongitude()!=0.0?mapGeneralParams.getLongitude():"");
		infoMap.put(MapsInfo.FIELD_MAP_TYPE, mapGeneralParams.getMapType().getFirstItem().getKey());
		infoMap.put(MapsInfo.FIELD_SENSOR, mapGeneralParams.getSensor());
		infoMap.put(MapsInfo.FIELD_ZOOM, mapGeneralParams.getZoom());
		
		MapDynamicParameters mapDynamicParams =  getMapDynamicParameters();
		infoMap.put(MapsInfo.FIELD_SHOW_DYNAMIC_MAP, mapDynamicParams.getShowDynamicMap());
		infoMap.put(MapsInfo.FIELD_DISABLE_DEFAULT_UI, mapDynamicParams.getDisableDefaultUI());
		infoMap.put(MapsInfo.FIELD_OVERVIEW_MAP_CONTROL, mapDynamicParams.getOverviewMapControl());
		infoMap.put(MapsInfo.FIELD_PAN_CONTROL, mapDynamicParams.getPanControl());
		infoMap.put(MapsInfo.FIELD_API_KEY, mapDynamicParams.getApiKey());
		infoMap.put(MapsInfo.FIELD_ROTATE_CONTROL, mapDynamicParams.getRotateControl());
		infoMap.put(MapsInfo.FIELD_SCALE_CONTROL, mapDynamicParams.getScaleControl());
		
		MapDynamicParametersSec mapDynamicParamesSec = getMapDynamicParametersSec();
		infoMap.put(MapsInfo.FIELD_SCROLL_WHEEL, mapDynamicParamesSec.getScrollwheel());
		infoMap.put(MapsInfo.FIELD_STREET_VIEW_CONTROL, mapDynamicParamesSec.getStreetViewControl());
		infoMap.put(MapsInfo.FIELD_DRAGGABLE, mapDynamicParamesSec.getDraggable());
		infoMap.put(MapsInfo.FIELD_DISABLE_DOUBLE_CLICK_ZOOM, mapDynamicParamesSec.getDisableDoubleClickZoom());
		infoMap.put(MapsInfo.FIELD_BACKGROUND_COLOR, mapDynamicParamesSec.getBackgroundColor());
		infoMap.put(MapsInfo.FIELD_MARKER_TITLE, mapDynamicParamesSec.getMarkerTitle());
		infoMap.put(MapsInfo.FIELD_TILT, mapDynamicParamesSec.getTilt().getFirstItem().getKey());
		
		MapStaticParameters mapStaticParams = getMapStaticParameters();
		infoMap.put(MapsInfo.FIELD_SHOW_STATICS_MAP, mapStaticParams.getShowStaticsMap());
		infoMap.put(MapsInfo.FIELD_CLIENT, mapStaticParams.getClient());
		infoMap.put(MapsInfo.FIELD_SIGNATURE, mapStaticParams.getSignature());
		infoMap.put(MapsInfo.FIELD_WIDTH, mapStaticParams.getWidth());
		infoMap.put(MapsInfo.FIELD_MARKER_LABEL, mapStaticParams.getMarkerLabel());
		infoMap.put(MapsInfo.FIELD_MARKER_COLOR, mapStaticParams.getMarkerColor().getFirstItem().getKey());
		
		return infoMap;
	}
}
