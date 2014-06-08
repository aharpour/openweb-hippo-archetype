#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.beans.compounds;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoMirror;

@Node(jcrType = "${namespace}:LatestItemsParemeters")
public class LatestItemsParemeters extends generated.beans.LatestItemsParemeters {
	
	private HippoBean contentBeanPath;

	@Override
	public HippoBean getContentBeanPath() {
		if (this.contentBeanPath == null) {
			this.contentBeanPath = this.<HippoMirror>getBean("${namespace}:contentBeanPath").getDeref(); 
		}
		return this.contentBeanPath;
	}

}
