package rudok.factory;

import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.view.PresentationView;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;

public class PresentationFactory extends AbstractNodeFactory{
    @Override
    public RuNode createNode(MyTreeNode myTreeNode) {
        Presentation presentation = new Presentation(myTreeNode.getNode(),"Mihail","Prezentacija "+(myTreeNode.getChildCount()+1),"src/rudok/images/pozadina.jpg");
        PresentationView presentationView = new PresentationView(presentation);
        ((Project) myTreeNode.getNode()).addChild(presentation); /// da li ovo treba pokrece i notify
        return presentation;
    }
}
