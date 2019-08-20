package testProgram;

import Program.Engines.FailSearchEngine;
import Program.Engines.IO.IOEngine;
import Program.Engines.PrintEngine;
import Program.System_Parts.Claster;
import Program.System_Parts.Initialize;
import org.junit.jupiter.api.Test;

import java.io.File;


public class TestMain {
    @Test
    void normal() {
        Claster claster = Initialize.InitializeClaster();
        FailSearchEngine failSearchEngine = new FailSearchEngine(claster);
        PrintEngine printEngine = new PrintEngine(claster);
        IOEngine serelizationEngine = new IOEngine(claster, IOEngine.Formats.JSON);

        File memoryPath = new File("claster.json");

        claster.sendMessage();
        System.out.println(failSearchEngine.search() + "\n");

        printEngine.print();

        serelizationEngine.save(memoryPath);
        Claster newClaster = serelizationEngine.load(memoryPath);

        PrintEngine newPrintEngine = new PrintEngine(newClaster);
        newPrintEngine.print();
    }

    @Test
    void failedPath() {
        Claster claster = Initialize.InitializeClaster();
        FailSearchEngine failSearchEngine = new FailSearchEngine(claster);
        PrintEngine printEngine = new PrintEngine(claster);
        IOEngine serelizationEngine = new IOEngine(claster, IOEngine.Formats.XML);

        File memoryPath = new File("failed Path");

        claster.sendMessage();
        System.out.println(failSearchEngine.search() + "\n");

        printEngine.print();

        serelizationEngine.save(memoryPath);
        Claster newClaster = serelizationEngine.load(memoryPath);

        PrintEngine newPrintEngine = new PrintEngine(newClaster);
        newPrintEngine.print();
    }

    @Test
    void withoutSendMessage() {
        Claster claster = Initialize.InitializeClaster();
        FailSearchEngine failSearchEngine = new FailSearchEngine(claster);
        PrintEngine printEngine = new PrintEngine(claster);
        IOEngine serelizationEngine = new IOEngine(claster, IOEngine.Formats.XML);

        File memoryPath = new File("claster.xml");

        System.out.println(failSearchEngine.search() + "\n");

        printEngine.print();

        serelizationEngine.save(memoryPath);
        Claster newClaster = serelizationEngine.load(memoryPath);

        PrintEngine newPrintEngine = new PrintEngine(newClaster);
        newPrintEngine.print();
    }
    @Test
    void withoutSaveClaster() {
        Claster claster = Initialize.InitializeClaster();
        FailSearchEngine failSearchEngine = new FailSearchEngine(claster);
        PrintEngine printEngine = new PrintEngine(claster);
        IOEngine serelizationEngine = new IOEngine(claster, IOEngine.Formats.JSON);

        File memoryPath = new File("claster.json");

        System.out.println(failSearchEngine.search() + "\n");

        printEngine.print();

        Claster newClaster = serelizationEngine.load(memoryPath);

        PrintEngine newPrintEngine = new PrintEngine(newClaster);
        newPrintEngine.print();
    }
}

