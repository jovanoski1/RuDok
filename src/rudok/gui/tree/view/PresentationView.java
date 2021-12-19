package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.Slide;
import rudok.model.workspace.dummyTreeNotification;
import rudok.observer.ISubscriber;
import rudok.state.StateManager;
import rudok.stateSlot.SlotStateManager;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements ISubscriber {

    private Presentation model;
    private JLabel autor  = new JLabel("");
    private JSplitPane splitPane;
    private JScrollPane previewScrollPane;
    private JScrollPane slideScrollPane;
    private JPanel previewPanel = new JPanel();
    private JPanel slidePanel = new JPanel();
    private JPanel slideShowPanel = new JPanel();
    private JPanel editPanel = new JPanel();
    private JToolBar presentationToolBar = new JToolBar();
    private JToolBar slideShowToolBar = new JToolBar();
    private JPanel cards = new JPanel();
    private JPanel navigationPanel = new JPanel();
    private JPanel authorAndToolbarPanel = new JPanel();
    private StateManager stateManager;
    private SlotStateManager slotStateManager;
    private JButton colorChooser;
    private JSpinner jSpinner = new JSpinner();
    private JComboBox<String> jComboBox = new JComboBox<>();

    public PresentationView(Presentation model){
        model.addSubscriber(this);
        this.model=model;
        this.setLayout(new BorderLayout());

        stateManager = new StateManager();
        slotStateManager = new SlotStateManager();

        initEditStatePanel();
        initSlideShowStatePanel();
        initControllers();

        this.add(editPanel, BorderLayout.CENTER);

        gui();
        refreshSlideShow();
    }
    private void initSlideShowStatePanel(){
        slideShowPanel.setLayout(new BorderLayout());
        slideShowToolBar.add(MainFrame.getInstance().getActionManager().getEditModeAction());
        slideShowPanel.add(slideShowToolBar, BorderLayout.NORTH);
        slideShowPanel.add(cards,BorderLayout.CENTER);
        slideShowPanel.add(navigationPanel, BorderLayout.SOUTH);
    }
    private void initEditStatePanel(){
        editPanel.setLayout(new BorderLayout());
        slidePanel.setLayout(new BoxLayout(slidePanel,BoxLayout.Y_AXIS));
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
        authorAndToolbarPanel.setLayout(new BorderLayout()); // panel for toolbar and authon label

        // init components for properties for adding slots
        colorChooser = new JButton("Pick color");
        colorChooser.setBackground(Color.RED);
        jSpinner.setMaximumSize(new Dimension(40,20));
        jSpinner.setPreferredSize(new Dimension(40,20));
        jSpinner.setMinimumSize(new Dimension(40,20));
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 20, 1);
        jSpinner.setModel(spinnerModel);
        jComboBox.setMaximumSize(new Dimension(60,20));
        jComboBox.setPreferredSize(new Dimension(60,20));
        jComboBox.setMinimumSize(new Dimension(60,20));
        jComboBox.addItem("Full");
        jComboBox.addItem("Dashed");

        // add actions to presentation toolbar
        presentationToolBar.add(MainFrame.getInstance().getActionManager().getSlideShowModeAction());
        presentationToolBar.add(new JSeparator(SwingConstants.VERTICAL));
        presentationToolBar.add(colorChooser);
        presentationToolBar.add(jSpinner);
        presentationToolBar.add(jComboBox);
        presentationToolBar.add(new JSeparator(SwingConstants.VERTICAL));
        presentationToolBar.add(MainFrame.getInstance().getActionManager().getAddSlotModeAction());
        presentationToolBar.add(MainFrame.getInstance().getActionManager().getDeleteSlotModeAction());
        presentationToolBar.add(MainFrame.getInstance().getActionManager().getSelectSlotAction());
        presentationToolBar.add(MainFrame.getInstance().getActionManager().getMoveSlotAction());

        authorAndToolbarPanel.add(autor, BorderLayout.CENTER);
        authorAndToolbarPanel.add(presentationToolBar, BorderLayout.NORTH);

        slideScrollPane = new JScrollPane(slidePanel);
        previewScrollPane = new JScrollPane(previewPanel);

        splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,previewScrollPane,slideScrollPane);
        splitPane.setDividerLocation(150);
        splitPane.setOneTouchExpandable(false);

        editPanel.add(authorAndToolbarPanel, BorderLayout.NORTH);
        editPanel.add(splitPane,BorderLayout.CENTER);
    }
    private void initControllers(){
        colorChooser.addActionListener(e -> {
            Color slotColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
            colorChooser.setBackground(slotColor);
            slotStateManager.getAddSlotState().setColor(slotColor);
        });
        jSpinner.addChangeListener(e -> slotStateManager.getAddSlotState().setWidth((Integer) jSpinner.getValue()));
        jComboBox.addActionListener(e -> {
            float lineType;
            if(jComboBox.getSelectedIndex() == 0)
                lineType = 0.1f;
            else{
                lineType=10.0f;
            }
            slotStateManager.getAddSlotState().setLineType(lineType);
        });
    }
    private void refreshSlideShow(){
        cards.removeAll();
        navigationPanel.removeAll();
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            if (model.getChildern().size()==0)return;
            cl.next(cards);
        });
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            if (model.getChildern().size()==0)return;
            cl.previous(cards);
        });
        navigationPanel.add(previousButton);
        navigationPanel.add(nextButton);

        cards.setLayout(new CardLayout());
        for(RuNode ruNode:model.getChildern()){
            if(ruNode instanceof Slide){
                cards.add((SlideView)ruNode.getSubscribers().get(2));
            }
        }
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
        //gui();
        if(notification instanceof dummyTreeNotification){
            if(((dummyTreeNotification) notification).getStatus().equals("added")){
                cards.add((SlideView)(((dummyTreeNotification) notification).getTreeNode()).getSubscribers().get(2));

                slidePanel.add((SlideView)(((dummyTreeNotification) notification).getTreeNode()).getSubscribers().get(0));
                slidePanel.add(Box.createVerticalStrut(15));

                previewPanel.add((SlideView)(((dummyTreeNotification) notification).getTreeNode()).getSubscribers().get(1));
                previewPanel.add(Box.createVerticalStrut(5));
            }
            else if(((dummyTreeNotification) notification).getStatus().equals("deleted")){
                cards.remove((SlideView)(((dummyTreeNotification) notification).getTreeNode()).getSubscribers().get(2));

                slidePanel.remove((SlideView)(((dummyTreeNotification) notification).getTreeNode()).getSubscribers().get(0));
                previewPanel.remove((SlideView)(((dummyTreeNotification) notification).getTreeNode()).getSubscribers().get(1));
            }
            //return;
        }
        gui();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }
    public void startEditState()
    {
        this.stateManager.setEditState();
        changeMode();
    }
    public void startSlideShowState(){
        this.stateManager.setSlideShowState();
        changeMode();
    }
    public void changeMode(){
        this.stateManager.getCurrentState().changeMode();
    }

    public void startAddSlotState(){
        this.slotStateManager.setAddSlotState();
    }
    public void startDeleteSlotState(){
        this.slotStateManager.setDeleteSlotState();
    }
    public void startMoveSlotState(){
        this.slotStateManager.setMoveSlotState();
    }
    public void startSelectSlotState(){ this.slotStateManager.setSelectSlotState();}
    public void slotAction(SlideView slideView,int x,int y){
        this.slotStateManager.getCurrentState().slotAction(slideView,x,y);
    }
    public void moveSlot(SlideView slideView,int x,int y){
        this.slotStateManager.getCurrentState().moveSlot(slideView,x,y);
    }

    public JPanel getEditPanel() {
        return editPanel;
    }

    public JPanel getSlideShowPanel() {
        return slideShowPanel;
    }

    public Presentation getModel() {
        return model;
    }
}
