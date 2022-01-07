package rudok.model.workspace;

import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;
import rudok.serialization.SerializableStrokeAdapter;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slot implements IPublisher, Serializable {

    private transient List<ISubscriber> subscribers;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private SerializableStrokeAdapter stroke;
    private double xScale;
    private double yScale;
    private boolean isSelected;
    private SlotType type;
    private String content;
    private Slide parent;

    public Slot(int x, int y, double xScale,double yScale,Color color, SlotType type, Slide parent) {
        this.x = x;
        this.y = y;
        this.width=40;
        this.height=30;
        this.xScale = xScale;
        this.yScale = yScale;
        this.color = color;
        this.type = type;
        this.parent = parent;
        content = "";
    }
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
        notifySubscribers(null);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifySubscribers(null);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifySubscribers(selected);
    }

    public SlotType getType() {
        return type;
    }

    public void setType(SlotType type) {
        this.type = type;
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
        return stroke.getStroke();
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = new SerializableStrokeAdapter(stroke);
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
        if(this.getSubscribers() == null || this.getSubscribers().isEmpty())
            return;

        for(ISubscriber listener : getSubscribers()){
            listener.update(notification);
        }
        if(! (notification instanceof Boolean))notifyProjectChange();
    }

    @Override
    public void notifyProjectChange() {
        parent.notifyProjectChange();
    }
}
