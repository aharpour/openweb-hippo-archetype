package ${package}.componentsinfo.catalog;

import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.DropDownList;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

import org.onehippo.forge.easyforms.behaviors.MailFormDataBehavior;
import org.onehippo.forge.easyforms.hst.EasyFormComponent;

@FieldGroupList({
	@FieldGroup(titleKey = "picker.group.title", value = { 
			FormWidgetInfo.FORM_FIELD}),
	@FieldGroup(titleKey = "mixin.group.title", value = { 
			FormWidgetInfo.FIELD_USE_FORM_MIXIN }),
	@FieldGroup(titleKey = "redirection.configuration.group", value = { 
			EasyFormComponent.ATTRIBUTE_DONE_REDIRECT }),
	@FieldGroup(titleKey = "behvior.configuration.group", value = { 
			FormWidgetInfo.STORE_BEHAVIOR, FormWidgetInfo.COUNTER_BEHAVIOR, 
			FormWidgetInfo.MAIL_BEHAVIOR,  FormWidgetInfo.CONFIRM_BEHAVIOR, 
			FormWidgetInfo.INTRO_BEHAVIOR, FormWidgetInfo.AFTER_BEHAVIOR}),
	@FieldGroup(titleKey = "submission.info.mail", value = { 
			MailFormDataBehavior.PARAM_FROM_EMAIL, MailFormDataBehavior.PARAM_FROM_NAME, 
			MailFormDataBehavior.PARAM_SUBJECT}),
	@FieldGroup(titleKey = "upload.configuration.group", value = { 
			FormWidgetInfo.FILE_UPLOAD_ENABLED, FormWidgetInfo.FILE_UPLOAD_METHOD,
			FormWidgetInfo.FILE_UPLOAD_DIRECTORY})
})
public interface FormWidgetInfo {

	public static final String FIELD_USE_FORM_MIXIN = "useFormMixin";
	public static final String FORM_FIELD = "formbean";
	
	public static final String STORE_BEHAVIOR = "storeBehaviour";
	public static final String COUNTER_BEHAVIOR = "counterBehavior";
	public static final String MAIL_BEHAVIOR = "mailBehavior";
	public static final String CONFIRM_BEHAVIOR = "confirmBehavior";
	public static final String INTRO_BEHAVIOR = "introBehavior";
	public static final String AFTER_BEHAVIOR = "afterBehavior";
	
	public static final String FILE_UPLOAD_ENABLED = "fileuploadEnabled";
	public static final String FILE_UPLOAD_METHOD = "fileuploadMethod";
	public static final String FILE_UPLOAD_DIRECTORY = "fileuploadDirectory";
	
	public static final String EMAIL = "email";
	public static final String FILESYSTEM = "filesystem";
	
	@Parameter(name = FORM_FIELD)
	@DocumentLink(docType = "ef:form", docLocation = "components/forms")
	public String getFormBean();
		
	@Parameter(name = FIELD_USE_FORM_MIXIN, defaultValue = "false")
	public boolean getUseFormMixin();
	
	@Parameter(name = EasyFormComponent.ATTRIBUTE_DONE_REDIRECT, defaultValue="")
	public String getEfDoneRedirect();
	
	@Parameter(name = MailFormDataBehavior.PARAM_FROM_EMAIL, defaultValue="")
	public String getEfFromEmail();
	
	@Parameter(name = MailFormDataBehavior.PARAM_FROM_NAME, defaultValue="")
	public String getEfFromName();
	
	@Parameter(name = MailFormDataBehavior.PARAM_SUBJECT, defaultValue="")
	public String getEfSubject();
	
	@Parameter(name = STORE_BEHAVIOR, defaultValue = "true")
	public boolean getStoreBehaviour();
	
	@Parameter(name = COUNTER_BEHAVIOR, defaultValue = "true")
	public boolean getCounterBehavior();
	
	@Parameter(name = MAIL_BEHAVIOR, defaultValue = "true")
	public boolean getMailBehavior();
	
	@Parameter(name = CONFIRM_BEHAVIOR, defaultValue = "true")
	public boolean getConfirmBehavior();
	
	@Parameter(name = INTRO_BEHAVIOR, defaultValue = "true")
	public boolean getIntroBehavior();
	
	@Parameter(name = AFTER_BEHAVIOR, defaultValue = "false")
	public boolean getAfterBehavior();
		
	@Parameter(name = FILE_UPLOAD_ENABLED, defaultValue = "false")
	public boolean getFileuploadEnabled();
	
	@Parameter(name = FILE_UPLOAD_METHOD, defaultValue="")
	@DropDownList(value = { FILESYSTEM, EMAIL })
	public String getFileuploadMethod();
	
	@Parameter(name = FILE_UPLOAD_DIRECTORY, defaultValue="")
	public String getFileuploadDirectory();

}
