package Program;

import Program.Engines.FailSearchEngine;
import Program.Engines.PrintEngine;
import Program.Engines.IO.IOEngine;
import Program.System_Parts.*;
import java.io.File;


public class Main {
    public static void main(String[] args) {

        Claster claster = Initialize.InitializeClaster();
        FailSearchEngine failSearchEngine = new FailSearchEngine(claster);
        PrintEngine printEngine = new PrintEngine(claster);
        IOEngine serelizationEngine = new IOEngine(claster, IOEngine.Formats.XML);

        File memoryPath = new File("asd");

        claster.sendMessage();
        System.out.println(failSearchEngine.search() + "\n");

        printEngine.print();

        serelizationEngine.save(memoryPath);
        Claster newClaster = serelizationEngine.load(memoryPath);

        PrintEngine newPrintEngine = new PrintEngine(newClaster);
        newPrintEngine.print();
    }
}

