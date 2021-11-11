package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.Slide;
import rudok.observer.ISubscriber;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements ISubscriber {

    private Presentation model;
    private JLabel autor  = new JLabel("");

    public PresentationView(Presentation model){
        model.addSubscriber(this);
        this.model=model;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        gui();
    }

    public Presentation getModel() {
        return model;
    }

    private void gui()
    {
        this.removeAll();
        autor.setText(model.getAutor());
        this.add(autor);

        for(RuNode ruNode : model.getChildern())
        {
            if(ruNode instanceof Slide){
                this.add((SlideView)ruNode.getSubscribers().get(0));
                this.add(Box.createVerticalStrut(15));
            }
        }
    }

    @Override
    public void update(Object notification) {
        /**if(notification instanceof String){
            model.getParent().notifySubscribers(notification); // rusi malo arhitekturu
            System.out.println("YSI");
            return;
        }*/
        // promena imena tako da se ne poziva iscrtavanje
        if(notification instanceof String){
           //provera za otvoreni projekat
            if(!MainFrame.getInstance().getProjectView().getModel().equals((Project)model.getParent()))return;
            MainFrame.getInstance().getProjectView().changeNameOfTab(((Project)model.getParent()).findChildIndex(model),(String)notification);
            return;
        }
        gui();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }
}
