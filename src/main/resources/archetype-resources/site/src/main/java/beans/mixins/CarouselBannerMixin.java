#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import ${package}.beans.compounds.CarouselCompoundMixin;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;


@Mixin("${namespace}:CarouselBannerMixin")
public interface CarouselBannerMixin {
	
	@JcrPath("${namespace}:carouselCompoundMixin")
	public CarouselCompoundMixin getCarouselCompoundMixin();

}