#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.compounds;

import java.util.HashMap;
import java.util.Map;

import org.hippoecm.hst.content.beans.Node;

import ${package}.componentsinfo.catalog.CarouselInfo;

@Node(jcrType = "${namespace}:CarouselCompoundMixin")
public class CarouselCompoundMixin extends generated.beans.CarouselCompoundMixin {
	
	public Map<String, Object> getConfigObject() {
		Map<String, Object> parametersInfoMap = new HashMap<String, Object>(2);
		parametersInfoMap.put(CarouselInfo.FIELD_SHOW_CAPTION, getCarouselParameters().getShowCaption());
		parametersInfoMap.put(CarouselInfo.FIELD_CAPTION_BACKGROUND_COLOR, getCarouselParameters().getCaptionBackgroundColor());
		parametersInfoMap.put(CarouselInfo.FIELD_FONT_COLOR, getCarouselParameters().getFontColor());
		return parametersInfoMap;
	}
}
