package rudok.view.popup;

import rudok.model.workspace.Presentation;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAutorDialog extends JDialog {

    private Presentation prezentacija;
    public EditAutorDialog(Presentation presentation) {

        this.prezentacija = presentation;
        gui();
        this.setTitle("Edit autor");
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setSize(400,100);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

    }

    private void gui(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("New autor:"));
        JTextField newAutor =  new JTextField(20);
        panel.add(newAutor);
        this.add(panel,BorderLayout.NORTH);
        JPanel dole= new JPanel(new FlowLayout());
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        dole.add(okButton);
        dole.add(cancelButton);
        this.add(dole,BorderLayout.SOUTH);
        okButton.addActionListener(e -> {
            String text= newAutor.getText();
            if(text.isBlank()){
                return; //TODO error;
            }
            prezentacija.setAutor(text);
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());
    }
}
