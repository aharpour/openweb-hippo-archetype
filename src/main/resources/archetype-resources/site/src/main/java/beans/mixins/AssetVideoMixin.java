#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import ${package}.beans.compounds.AssetVideoCompoundMixin;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:AssetVideoMixin")
public interface AssetVideoMixin {
	
	@JcrPath("${namespace}:assetVideoCompoundMixin")
	public AssetVideoCompoundMixin getAssetVideoCompoundMixin();

}