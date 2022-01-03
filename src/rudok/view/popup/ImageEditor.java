package rudok.view.popup;

import rudok.model.workspace.Slot;
import rudok.view.MainFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEditor extends JDialog {
    private Slot slot;
    public ImageEditor(Slot slot) {
        this.slot = slot;
        gui();
        this.setTitle("Image Editor");
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setSize(600,400);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }
    private void gui(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Image preview:"));
        JPanel imagePreview = new JPanel();
        imagePreview.setSize(new Dimension(150,150));
        imagePreview.setMaximumSize(new Dimension(600,300));
        panel.add(imagePreview);
        this.add(panel,BorderLayout.NORTH);
        JPanel dole = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton openButton = new JButton("Open");
        dole.add(saveButton);
        dole.add(openButton);
        this.add(dole,BorderLayout.SOUTH);
        saveButton.addActionListener(e -> {
            dispose();
        });
        openButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.showOpenDialog(MainFrame.getInstance());
            File file = jFileChooser.getSelectedFile();
            if(file==null)return;
            BufferedImage myPicture = null;
            try {
                myPicture = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            imagePreview.add(picLabel);
            SwingUtilities.updateComponentTreeUI(imagePreview);
        });
    }
}
