package com.codecool.singletonDojo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class PrinterTest {

    @Test
    void getInstance() {
        Printer printer1 = Printer.getInstance();
        Printer printer2 = Printer.getInstance();
        assertSame(printer1, printer2, "These are the same");
    }

    @Test
    void getAvailableInstanceIsNotNull() throws InterruptedException {
        assertNotNull(Printer.getAvailableInstance());
    }

    @Test
    void getAvailableInstanceIsTheSame() throws InterruptedException {
        List<Printer> printerList1 = Printer.getPrinterList();
        List<Printer> printerList2 = Printer.getPrinterList();
        assertSame(printerList1, printerList2, "This does not work");
    }

}