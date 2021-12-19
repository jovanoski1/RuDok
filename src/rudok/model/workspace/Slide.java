package rudok.model.workspace;

import rudok.model.tree.RuNode;
import java.util.ArrayList;
import java.util.List;

public class Slide extends RuNode {
    private int redniBroj;
    private List<Slot> slots = new ArrayList<>();

    public Slide(int redniBroj,RuNode parent)
    {
        this.redniBroj=redniBroj;
        setIme("Slide "+redniBroj);
        if(parent instanceof Presentation)setParent(parent);
    }

    public void addSlot(Slot s){
        if(!slots.contains(s)){
            slots.add(s);
            notifySubscribers(new dummySlotNotification(s,"added"));
        }
    }
    public void removeSlot(Slot s){
        if(slots.contains(s)){
            slots.remove(s);
            notifySubscribers(new dummySlotNotification(s,"deleted"));
        }
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }
}
