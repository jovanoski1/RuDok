package rudok.errors;

import java.awt.*;

public class EmptyAuthorNameAError extends AError {

    @Override
    public String showError() {
        return "Autor name cannot be empty!";
    }
}
