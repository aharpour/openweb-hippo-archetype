#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import ${package}.beans.compounds.RssCompoundMixin;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:RssMixin")
public interface RssMixin {
	
	@JcrPath("${namespace}:rssCompoundMixin")
	public RssCompoundMixin getRssCompoundMixin();

}