#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import ${package}.beans.compounds.LatestItemsCompoundMixin;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:LatestItemsMixin")
public interface LatestItemsMixin {
	
	@JcrPath("${namespace}:latestItemsCompoundMixin")
	public LatestItemsCompoundMixin getLatestItemsCompoundMixin();

}
