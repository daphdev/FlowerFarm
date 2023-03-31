import factory.ControllerFactory;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> ControllerFactory.create("Main"));
    }

}
