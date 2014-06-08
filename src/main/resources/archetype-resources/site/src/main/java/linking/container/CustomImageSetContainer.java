#set($hyphen = '-')
#set($empty = '')
#set($cleanArtifactId = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.linking.container;

import org.hippoecm.hst.core.linking.AbstractResourceContainer;

public class CustomImageSetContainer extends AbstractResourceContainer {
	
	public String getNodeType() {
		return "${cleanArtifactId}:${cleanArtifactId.substring(0,1).toUpperCase()}${cleanArtifactId.substring(1)}ImageSet";
	}
}