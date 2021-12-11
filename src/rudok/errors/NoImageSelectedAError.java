package rudok.errors;

import java.awt.*;

public class NoImageSelectedAError extends AError {

    @Override
    public String showError() {
        return "Please select image!";
    }
}
