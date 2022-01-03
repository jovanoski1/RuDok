package rudok.view.popup;

import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;
import rudok.view.MainFrame;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class TextEditorDialog extends JDialog {
    private SlotView slotView;

    private JButton boldButton;
    private JButton italicButton;
    private JButton underlineButton;
    private JButton saveButton;
    private JTextPane textPane;

    public TextEditorDialog(SlotView slotview) {
        this.slotView = slotview;
        gui();
        controllers();
        this.setTitle("Text Editor");
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setSize(300,200);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }
    private void gui() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JToolBar jToolBar = new JToolBar();
        boldButton = new JButton(new ImageIcon("src/rudok/actions/icons/bold.png"));
        boldButton.setToolTipText("Bold");
        italicButton = new JButton(new ImageIcon("src/rudok/actions/icons/italic.png"));
        italicButton.setToolTipText("Italic");
        underlineButton = new JButton(new ImageIcon("src/rudok/actions/icons/underline.png"));
        underlineButton.setToolTipText("Underline");
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 24));

        jToolBar.add(boldButton);
        jToolBar.add(italicButton);
        jToolBar.add(underlineButton);
        jToolBar.add(new JSeparator(SwingConstants.VERTICAL));
        jToolBar.add(saveButton);
        panel.add(jToolBar,BorderLayout.NORTH);

        textPane = new JTextPane();
        textPane.setText(slotView.getSlotHandler().readContent(slotView.getModel()));
        panel.add(textPane);
        this.add(panel);
    }

    private void controllers(){
        boldButton.addActionListener(new StyledEditorKit.BoldAction());
        italicButton.addActionListener(new StyledEditorKit.ItalicAction());
        underlineButton.addActionListener(new StyledEditorKit.UnderlineAction());
        saveButton.addActionListener(e -> {
            slotView.getSlotHandler().setContent(slotView.getModel(), textPane.getText());
            this.dispose();
        });
    }
}
