package rudok.errors;

public class EmptyAuthorNameAError extends AError {

    @Override
    public String showError() {
        return "Autor name cannot be empty!";
    }
}
