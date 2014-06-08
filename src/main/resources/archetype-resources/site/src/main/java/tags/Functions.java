package ${package}.tags;

import java.util.LinkedHashMap;

import org.onehippo.forge.easyforms.model.ErrorMessage;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.core.component.HstRequest;

import ${package}.channels.WebsiteInfo;


public class Functions {
	
	private Functions() {
	}
    
    public static String getFieldError(LinkedHashMap<String, ErrorMessage> errors, String fieldName) {
       String result = "";
       if(errors != null){    	   
    	   if(errors.get(fieldName) != null){
    		   result = errors.get(fieldName).getLocalizedMessage();
    	   }
       }
       return result;
    }
    
    public static boolean isFieldValid(LinkedHashMap<String, ErrorMessage> errors, String fieldName) {
        boolean result = true;
        if(errors != null){    	   
   		   if(!errors.get(fieldName).getLocalizedMessage().isEmpty()){
   			   result = false;
   		   }
        }
        return result;
     }
    
    public static String getDefaultBrowserTitle(HstRequest request){
		String result = "";
		final Mount mount = request.getRequestContext().getResolvedMount().getMount();
		final WebsiteInfo info = mount.getChannelInfo();
		result = info.getDefaultBrowserTitle();

		return result;
	}
}
