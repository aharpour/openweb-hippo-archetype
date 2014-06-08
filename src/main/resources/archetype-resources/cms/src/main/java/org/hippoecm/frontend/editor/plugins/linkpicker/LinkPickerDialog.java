
package org.hippoecm.frontend.editor.plugins.linkpicker;

import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.hippoecm.frontend.PluginRequestTarget;
import org.hippoecm.frontend.dialog.AbstractDialog;
import org.hippoecm.frontend.model.JcrNodeModel;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.hippoecm.frontend.plugins.standards.picker.NodePickerController;
import org.hippoecm.frontend.plugins.standards.picker.NodePickerControllerSettings;
import org.hippoecm.frontend.session.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkPickerDialog extends AbstractDialog<String> {

    private static final long serialVersionUID = 1L;

    static final Logger log = LoggerFactory.getLogger(LinkPickerDialog.class);

    private final IPluginContext context;
    private final IPluginConfig config;

    private final NodePickerController controller;

    @SuppressWarnings("serial")
	public LinkPickerDialog(IPluginContext context, IPluginConfig config, IModel<String> model) {
        super(model);

        this.context = context;
        this.config = config;
       
        setOutputMarkupId(true);
                
        controller = new NodePickerController(context, NodePickerControllerSettings.fromPluginConfig(config)) {

            @Override
            protected IModel<Node> getInitialModel() {
                try {
                    String uuid = getModelObject();
                    if (uuid != null && !"".equals(uuid)) {
                        return new JcrNodeModel(((UserSession) Session.get()).getJcrSession().getNodeByIdentifier(uuid));
                    }
                } catch (RepositoryException ex) {
                    log.error(ex.getMessage());
                }
                return null;
            }

            @Override
            protected void onSelect(boolean isValid) {
                setOkEnabled(isValid);
            }

        };

        add(controller.create("content"));
    }
    
    //TODO: new constructor, receiving Locale as new parameter and calling the new NodePickerController constructor
    //which puts the Locale in use 
    @SuppressWarnings("serial")
	public LinkPickerDialog(IPluginContext context, IPluginConfig config, IModel<String> model, Locale loc) {
        super(model);

        this.context = context;
        this.config = config;
       
        setOutputMarkupId(true);
        
        controller = new NodePickerController(context, NodePickerControllerSettings.fromPluginConfig(config), loc) {

            @Override
            protected IModel<Node> getInitialModel() {
                try {
                    String uuid = getModelObject();
                    if (uuid != null && !"".equals(uuid)) {
                        return new JcrNodeModel(((UserSession) Session.get()).getJcrSession().getNodeByIdentifier(uuid));
                    }
                } catch (RepositoryException ex) {
                    log.error(ex.getMessage());
                }
                return null;
            }

            @Override
            protected void onSelect(boolean isValid) {
                setOkEnabled(isValid);
            }

        };

        add(controller.create("content"));
    }


    public IModel<String> getTitle() {
        return new StringResourceModel("link-picker", this, null);
    }

    @Override
    public final void onClose() {
        controller.onClose();
    }

    @Override
    public void render(PluginRequestTarget target) {
        if (controller.getRenderer() != null) {
            controller.getRenderer().render(target);
        }
        super.render(target);
    }

    protected IModel<Node> getFolderModel() {
        return controller.getFolderModel();
    }

    @Override
    public void onOk() {
        if (controller.getSelectedModel() != null) {
            saveNode(controller.getSelectedModel().getObject());
        } else {
            error("No node selected");
        }
    }

    @Override
    protected void onDetach() {
        super.onDetach();

        controller.detach();
    }

    protected void saveNode(Node node) {
        try {
            getModel().setObject(node.getIdentifier());
        } catch (RepositoryException ex) {
            error(ex.getMessage());
        }
    }


    protected IPluginContext getPluginContext() {
        return context;
    }

    protected IPluginConfig getPluginConfig() {
        return config;
    }

}
