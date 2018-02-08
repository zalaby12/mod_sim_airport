package edu.cx4230.simulator.util;

import edu.cx4230.simulator.App;

public class Print {

    public static void line(String message) {
        if (App.debugOn()) {
            System.out.println(message);
        }
    }

}
