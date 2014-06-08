#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import generated.beans.YoutubeCompoundMixin;
import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

@Mixin("${namespace}:YoutubeMixin")
public interface YoutubeMixin {
	
	@JcrPath("${namespace}:youtubeCompoundMixin")
	public YoutubeCompoundMixin getYoutubeCompoundMixin();

}