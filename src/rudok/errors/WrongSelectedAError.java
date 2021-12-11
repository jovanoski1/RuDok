package rudok.errors;

import java.awt.*;

public class WrongSelectedAError extends AError {
    @Override
    public String showError() {
        return "Please select Presentation!";
    }
}
