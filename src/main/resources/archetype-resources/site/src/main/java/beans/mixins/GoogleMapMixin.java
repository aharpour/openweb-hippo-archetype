#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import ${package}.beans.compounds.MapCompoundMixin;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:GoogleMapMixin")
public interface GoogleMapMixin {
	
	@JcrPath("${namespace}:mapCompoundMixin")
	public MapCompoundMixin getMapCompoundMixin();

}