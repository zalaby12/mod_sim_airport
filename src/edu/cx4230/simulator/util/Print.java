package edu.cx4230.simulator.util;

import edu.cx4230.simulator.App;

import java.io.PrintWriter;

public class Print {

    public static final String FOLDER = "./FLIGHTS/";
    public static final String FILE_EXTENSION = ".txt";

    public static void line(String message) {
        if (App.debugOn()) {
            System.out.println(message);
        }
    }

    public static void writer(Writeable object) {
       if (App.debugOn()) {
           try (PrintWriter writer = new PrintWriter(FOLDER + object.fileName() + FILE_EXTENSION)) {
               writer.print(object.fileText());
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       }
    }

}
