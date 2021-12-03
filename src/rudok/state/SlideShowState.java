package rudok.state;

public class SlideShowState implements State{
    @Override
    public void changeMode() {
        System.out.println("SLIDESHOW MODE");
    }
}
