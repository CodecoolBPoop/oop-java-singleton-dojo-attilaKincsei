package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Printer {

    private int id; // ID of the printer. Unique.
    private LocalTime busyEndTime;
    private static Printer printerInstance = null;
    private static List<Printer> printerList = null;

    private Printer(int id) {
        this.id = id;
    }

    static Printer getInstance() {
        if (printerInstance == null) {
            printerInstance = new Printer(0);
        }
        printerInstance.setPrinterBusyAtRandom();
        return printerInstance;
    }

    static Printer getAvailableInstance() throws InterruptedException {
        if (printerList == null) {
            printerList = new ArrayList<>();
            generatePrinters();
            Thread.sleep(1000);
            for (Printer printer : printerList) {
                if (printer.isAvailable()) {
                    System.out.println("Printer " + printer.getId() + " is available.");
                    return printer;
                }
            }
        }
        Random random = new Random();
        System.out.println("Random printer is picked");
        return printerList.get(random.nextInt(11));
    };

    private static void generatePrinters() {
        for (int i = 1; i <= 10; i++) {
            Printer printer = new Printer(i);
            printer.setPrinterBusyAtRandom();
            printerList.add(printer);
        }
    }

    private void setPrinterBusyAtRandom() {
        Random randomGen = new Random();
        this.busyEndTime = LocalTime.now().plusSeconds(randomGen.nextInt(5));;
    }

    // Prints out the given String
    void print(String toPrint) {
        // Its not needed to actually print with a printer in this exercise
        System.out.println("Printer " + id + " is printing.");
        System.out.println(toPrint);
        for (Printer printer : printerList) {
            System.out.printf("Printer %d is available: %b", printer.getId(), printer.isAvailable());
            System.out.println();
        }
    }

    // Returns true if the printer is ready to print now.
    private boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }


    private int getId() {
        return id;
    }

    public static List<Printer> getPrinterList() {
        return printerList;
    }
}
