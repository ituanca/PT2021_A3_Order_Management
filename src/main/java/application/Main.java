package application;

import presentationLayer.controller.Controller;

/**
 * Main class
 * @author Anca
 */
public class Main {

    /**
     * starts the application by calling the start method from controller
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        controller.start();
    }

}
