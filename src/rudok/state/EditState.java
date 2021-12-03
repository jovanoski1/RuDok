package rudok.state;

public class EditState  implements State{
    @Override
    public void changeMode() {
        System.out.println("EDIT MODE");
    }
}
