#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set($hyphen = '-')
#set($empty = '')
#set($namespace = ${rootArtifactId.replace($hyphen,$empty)})
package ${package}.channels;

import ${package}.utils.Constants;
import org.hippoecm.hst.configuration.channel.ChannelInfo;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.JcrPath;
import org.hippoecm.hst.core.parameters.Parameter;

import com.tdclighthouse.prototype.utils.Constants.PikcerTypes;

/**
 * Retrieves the properties of a website channel.
 */
@FieldGroupList({
        @FieldGroup(
                titleKey = "fields.website",
                value = { "headerName", "logoPath", "defaultBrowserTitle", "defaultPage" }
        )
})
public interface WebsiteInfo extends ChannelInfo {

    @Parameter(name = "headerName", defaultValue = "HST Website")
    String getHeaderName();
    
    @Parameter(name = "logoPath", defaultValue = "/content/gallery/${namespace}/logos/logo_openweb.png")
    @JcrPath(
    		pickerConfiguration = PikcerTypes.IMAGE_PICKER,
    		pickerSelectableNodeTypes = { "${namespace}:${namespace.substring(0,1).toUpperCase()}${namespace.substring(1)}ImageSet" },
    		pickerInitialPath = "/content/gallery/${namespace}/logos/"
    		)
    public String getLogoPath();
    
    @Parameter(name = "defaultBrowserTitle", defaultValue = "${namespace}")
    String getDefaultBrowserTitle();
    
    @Parameter(name = "defaultPage")
    @JcrPath(
    		pickerConfiguration = Constants.PikcerTypes.DOCUMENT_PICKER,
            pickerInitialPath = "/content/documents"
    )
    String getLanguageDefaultPage();

}

