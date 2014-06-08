#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import generated.beans.BannerCompoundMixin;
import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:BannerMixin")
public interface BannerMixin {
	
	@JcrPath("${namespace}:bannerCompoundMixin")
	public BannerCompoundMixin getBannerCompoundMixin();

}