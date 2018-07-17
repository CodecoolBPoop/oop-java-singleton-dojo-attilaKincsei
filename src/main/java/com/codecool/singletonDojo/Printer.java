package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Printer {

    private int id; // ID of the printer. Unique.
    private LocalTime busyEndTime;
    private static final Printer PRINTER_INSTANCE = new Printer(0);
    private static List<Printer> printerList = new ArrayList<>();

    private Printer(int id) {
        this.id = id;
    }

    private static void generatePrinters(Integer numberOfPrinters) {
        for (int i = 1; i <= numberOfPrinters; i++) {
            Random randomGen = new Random();
            LocalTime busyEndTime = LocalTime.now().plusSeconds(randomGen.nextInt(5));
            Printer printer = new Printer(i);
            printer.busyEndTime = busyEndTime;
            printerList.add(printer);
        }
    }

    static Printer getInstance() {
        Random randomGen = new Random();
        PRINTER_INSTANCE.busyEndTime = LocalTime.now().plusSeconds(randomGen.nextInt(5));
        return PRINTER_INSTANCE;
    }

    static Printer getAvailableInstance() throws InterruptedException {
        generatePrinters(10);
        Thread.sleep(1000);
        for (Printer printer : printerList) {
            if (printer.isAvailable()) {
                System.out.println("Printer " + printer.getId() + " is available.");
                return printer;
            }
        }
        Random random = new Random();
        System.out.println("Random printer is picked");
        return printerList.get(random.nextInt(10));
    };

    // Prints out the given String
    void print(String toPrint) {
        // Its not needed to actually print with a printer in this exercise
        System.out.println("Printer " + id + " is printing.");
        System.out.println(toPrint);
    }

    // Returns true if the printer is ready to print now.
    private boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }


    public int getId() {
        return id;
    }
}
