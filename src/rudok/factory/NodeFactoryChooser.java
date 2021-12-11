package rudok.factory;

import rudok.model.workspace.RuNodeType;

public class NodeFactoryChooser {
    private static ProjectFactory projectFactory = new ProjectFactory();
    private static PresentationFactory presentationFactory = new PresentationFactory();
    private static SlideFactory slideFactory = new SlideFactory();

    public static AbstractNodeFactory getFactory(RuNodeType type){
        if(type.equals(RuNodeType.WORKSPACE))return projectFactory;
        else if(type.equals(RuNodeType.PROJECT))return presentationFactory;
        else if (type.equals(RuNodeType.PRESENTATION))return slideFactory;
        return null;
    }
}
