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
        IOEngine serelizationEngine = new IOEngine(claster, IOEngine.Formats.JSON);

        File memoryPath = new File("C:\\Users\\KaJLaZ\\IdeaProjects\\JavaLabTask1\\claster.json");

        claster.sendMessage();
        System.out.println(failSearchEngine.search() + "\n");

        printEngine.print();

        serelizationEngine.save(memoryPath, claster);
        Claster newClaster = serelizationEngine.load(memoryPath);

        PrintEngine newPrintEngine = new PrintEngine(newClaster);
        newPrintEngine.print();
    }
}

