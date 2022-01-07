package rudok.view.popup;

import rudok.Utils;
import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Project;
import rudok.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class WorkspaceChooserDialog extends JDialog {

    private JLabel jLabel;
    private JButton openButton;
    private JButton okButton;
    private JButton continueButton;
    private File file;
    public WorkspaceChooserDialog() {
        gui();
        controllers();
        this.setTitle("Workspace chooser");
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setSize(300,200);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    private void gui(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        jLabel = new JLabel();
        openButton = new JButton("Open");
        okButton = new JButton("Ok");
        continueButton = new JButton("Continue with last workspace");
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(jLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(continueButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(openButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(okButton);
        this.add(panel);
    }

    private void controllers(){

        openButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GPF files", "txt"));
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.showOpenDialog(MainFrame.getInstance());
            file = jFileChooser.getSelectedFile();
            if(file==null)return;
            jLabel.setText(file.getPath());
        });

        okButton.addActionListener(e -> {
            if (file == null)return;
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file.getPath()));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    if(!line.contains(".gpf"))break;

                    ///
                    /*ObjectInputStream os = null;
                    try {
                        os = new ObjectInputStream(new
                                FileInputStream(line));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Project p = null;
                    try {
                        //poziv metode readObject() koja vrši dubinsku deserijalizaciju
                        assert os != null;
                        p = (Project) os.readObject();
                    } catch (ClassNotFoundException | IOException ez) {
                        ez.printStackTrace();
                    }*/

                    Utils.openProject(line);
                    ///
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        });

        continueButton.addActionListener(e -> {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(Utils.getLastWorkspacePath()));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    if(!line.contains(".gpf"))break;
                    Utils.openProject(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        });

    }

}
