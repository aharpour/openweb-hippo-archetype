package project.${package}.dashboardshortcuts;

import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.onehippo.forge.dashboard.documentwizard.NewDocumentWizardPlugin;

import java.rmi.RemoteException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;

import org.hippoecm.frontend.session.UserSession;
import org.hippoecm.repository.api.HippoNode;
import org.hippoecm.repository.api.HippoWorkspace;
import org.hippoecm.repository.api.Workflow;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.api.WorkflowManager;
import org.hippoecm.repository.standardworkflow.FolderWorkflow;

public class FormShortcutConfig extends NewDocumentWizardPlugin {
	
	private static final long serialVersionUID = 1L;

	private static final String FOLDER_TYPE = "new-forms-folder";
    private static final String DOCUMENT_TYPE = "new-easy-form";

    /**
     * This class creates a link on the dashboard. The link opens a dialog that allow the user to quickly create a
     * document. The location of the document can be configured.
     */
    public FormShortcutConfig(final IPluginContext context, final IPluginConfig config) {
        super(context, config);
    }

    @Override
    protected HippoNode createFolder(HippoNode parentNode, String name) throws RepositoryException, RemoteException, WorkflowException {
            	
    	Session session = ((UserSession) getSession()).getJcrSession();
        HippoWorkspace workspace = (HippoWorkspace) session.getWorkspace();
        WorkflowManager workflowMgr = workspace.getWorkflowManager();

        // get the folder node's workflow
        Workflow workflow = workflowMgr.getWorkflow("internal", parentNode);

        if (workflow instanceof FolderWorkflow) {
            FolderWorkflow fw = (FolderWorkflow) workflow;

            // create the new folder
            String category = "new-folder";
            NodeType[] mixinNodeTypes = parentNode.getMixinNodeTypes();
            for (int i = 0; i < mixinNodeTypes.length; i++) {
                NodeType mixinNodeType = mixinNodeTypes[i];
                if (mixinNodeType.getName().equals("hippotranslation:translated")) {
                	category = "new-translated-folder";
                    break;
                }
            }
            fw.add(category, "hippostd:folder", name);

            HippoNode newFolder = (HippoNode) parentNode.getNode(name);            
            newFolder.setProperty("hippostd:foldertype", new String[]{DOCUMENT_TYPE, FOLDER_TYPE});

            return newFolder;
        } else {
            throw new WorkflowException("Workflow is not an instance of FolderWorkflow");
        }
    }	
}
