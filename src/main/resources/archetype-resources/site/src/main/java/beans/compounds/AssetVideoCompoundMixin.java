#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.compounds;

import java.util.HashMap;
import java.util.Map;

import org.hippoecm.hst.content.beans.Node;

import ${package}.componentsinfo.catalog.VideoInfo;

@Node(jcrType = "${namespace}:AssetVideoCompoundMixin")
public class AssetVideoCompoundMixin extends generated.beans.AssetVideoCompoundMixin{

	public Map<String, Object> getConfigObject() {
		Map<String, Object> infoMap = new HashMap<String, Object>(2);
		infoMap.put(VideoInfo.ALLOW_FULL_SCREEN, getAssetVideoParameters().getAllowFullScreen());
		infoMap.put(VideoInfo.WIDTH, getAssetVideoParameters().getWidth());
		infoMap.put(VideoInfo.HEIGHT, getAssetVideoParameters().getHeight());
		return infoMap;
	}
}
