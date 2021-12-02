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
    private JSplitPane splitPane;
    private JScrollPane previewScrollPane;
    private JScrollPane slideScrollPane;
    private JPanel previewPanel = new JPanel();
    private JPanel slidePanel = new JPanel();

    public PresentationView(Presentation model){
        model.addSubscriber(this);
        this.model=model;
        //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        this.add(autor,BorderLayout.NORTH);

        slidePanel.setLayout(new BoxLayout(slidePanel,BoxLayout.Y_AXIS));
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));


        slideScrollPane = new JScrollPane(slidePanel);
        previewScrollPane = new JScrollPane(previewPanel);

        splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,previewScrollPane,slideScrollPane);
        splitPane.setDividerLocation(150);
        splitPane.setOneTouchExpandable(false);
        this.add(splitPane,BorderLayout.CENTER);
        gui();
    }

    public Presentation getModel() {
        return model;
    }

    private void gui()
    {
        //this.removeAll();
        slidePanel.removeAll();
        previewPanel.removeAll();
        autor.setText(model.getAutor());
        for(RuNode ruNode : model.getChildern())
        {
            if(ruNode instanceof Slide){
                slidePanel.add((SlideView)ruNode.getSubscribers().get(0));
                slidePanel.add(Box.createVerticalStrut(15));

                previewPanel.add((SlideView)ruNode.getSubscribers().get(1));
                previewPanel.add(Box.createVerticalStrut(5));
            }
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }

    @Override
    public void update(Object notification) {
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
