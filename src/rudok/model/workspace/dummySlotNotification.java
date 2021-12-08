package rudok.model.workspace;

public class dummySlotNotification {
    Slot slot;
    String status;

    public dummySlotNotification(Slot s, String status) {
        this.slot = s;
        this.status = status;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot s) {
        this.slot = s;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
