package rudok.factory;

import rudok.gui.tree.view.PresentationView;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;

public class PresentationFactory extends AbstractNodeFactory{
    @Override
    public RuNode createNode(RuNode ruNode) {
        Presentation presentation = new Presentation(ruNode,"Mihail","Prezentacija "+((((Project)ruNode).getChildern().size()+1)),"src/rudok/images/pozadina.jpg");
        PresentationView presentationView = new PresentationView(presentation);
        ((Project) ruNode).addChild(presentation); /// da li ovo treba pokrece i notify
        return presentation;
    }
}
