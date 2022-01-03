package rudok.view.popup;

import rudok.gui.tree.view.SlotView;
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
    private SlotView slotView;
    File file;
    ImagePanel imagePanel;
    public ImageEditor(SlotView slotView) {
        this.slotView = slotView;
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
        //JPanel imagePreview = new JPanel();
        imagePanel = new ImagePanel(slotView.getSlotHandler().readContent(slotView.getModel()));
        imagePanel.setMinimumSize(new Dimension(600,300));
        imagePanel.setPreferredSize(new Dimension(600,300));
        imagePanel.setMaximumSize(new Dimension(600,300));
        panel.add(imagePanel);
        this.add(panel,BorderLayout.NORTH);
        JPanel dole = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton openButton = new JButton("Open");
        dole.add(saveButton);
        dole.add(openButton);
        this.add(dole,BorderLayout.SOUTH);

        String slotContent = slotView.getSlotHandler().readContent(slotView.getModel());
        System.out.println(slotContent);


        saveButton.addActionListener(e -> {
            if(file==null)return;
            slotView.getSlotHandler().setContent(slotView.getModel(), file.getPath());
            dispose();
        });
        openButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.showOpenDialog(MainFrame.getInstance());
            file = jFileChooser.getSelectedFile();
            if(file==null)return;
            imagePanel.setUrl(file.getPath());
            imagePanel.repaint();
        });
    }
}
