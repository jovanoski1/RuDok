package rudok.errors;

import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;
import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements IPublisher {

    private static ErrorFactory insance;
    private List<ISubscriber> subscribers=new ArrayList<>();

    private ErrorFactory(){

    }

    public void createError(String type){
        if(type.equals("autorEmpty")){
            notifySubscribers(new EmptyAuthorNameAError());
        }
        else if(type.equals("wrongSelected")){
            notifySubscribers(new WrongSelectedAError());
        }
        else if(type.equals("workspaceDelete")){
            notifySubscribers(new WorkspaceDeleteAError());
        }
        else if(type.equals("noImage")){
            notifySubscribers(new NoImageSelectedAError());
        }
    }


    public static ErrorFactory getInsance() {
        if(insance==null)insance=new ErrorFactory();
        return insance;
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
