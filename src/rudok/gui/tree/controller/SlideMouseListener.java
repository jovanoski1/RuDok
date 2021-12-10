package rudok.gui.tree.controller;

import rudok.gui.tree.view.SlideView;
import rudok.model.workspace.Slide;
import rudok.model.workspace.Slot;
import rudok.view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SlideMouseListener implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(((SlideView)e.getComponent()).getModel().getIme());
        SlideView s = ((SlideView)e.getComponent());
        MainFrame.getInstance().getProjectView().getPresentetion().slotAction(s, e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        SlideView s = ((SlideView)e.getComponent());
        MainFrame.getInstance().getProjectView().getPresentetion().moveSlot(s,e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
