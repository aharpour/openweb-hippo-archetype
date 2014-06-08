#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:DiscussMixin")
public interface DiscussMixin {
	
}