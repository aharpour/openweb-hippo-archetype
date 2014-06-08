#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.mixins;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

import org.onehippo.forge.easyforms.beans.FormBean;

@Mixin("${namespace}:EasyFormMixin")
public interface EasyFormMixin {
	
	@JcrPath("${namespace}:formPicker")
	public FormBean getFormBean();

}
