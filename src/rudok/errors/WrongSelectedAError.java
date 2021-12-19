package rudok.errors;

public class WrongSelectedAError extends AError {
    @Override
    public String showError() {
        return "Please select Presentation!";
    }
}
