package rudok.errors;


public class NoImageSelectedAError extends AError {

    @Override
    public String showError() {
        return "Please select image!";
    }
}
