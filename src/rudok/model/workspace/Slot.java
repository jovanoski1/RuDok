package rudok.model.workspace;

import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Slot implements IPublisher {

    private List<ISubscriber> subscribers;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private Stroke stroke;
    private double xScale;
    private double yScale;

    public Slot(int x, int y, double xScale,double yScale,Color color) {
        this.x = x;
        this.y = y;
        this.width=40;
        this.height=30;
        this.xScale = xScale;
        this.yScale = yScale;
        this.color = color;
    }
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
        notifySubscribers(new Object());
    }

    public int getX() {
        return x;
    }

    public double getxScale() {
        return xScale;
    }

    public void setxScale(double xScale) {
        this.xScale = xScale;
    }

    public double getyScale() {
        return yScale;
    }

    public void setyScale(double yScale) {
        this.yScale = yScale;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.getSubscribers() ==null)
            this.setSubscribers(new ArrayList<>());
        if(this.getSubscribers().contains(sub))
            return;
        this.getSubscribers().add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.getSubscribers() == null || !this.getSubscribers().contains(sub))
            return;
        this.getSubscribers().remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.getSubscribers() == null || this.getSubscribers().isEmpty())
            return;

        for(ISubscriber listener : getSubscribers()){
            listener.update(notification);
        }
    }
}
