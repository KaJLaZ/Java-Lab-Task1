package testProgram.Engines;

import Program.Engines.Engine;
import Program.Engines.FailSearchEngine;
import Program.Engines.PrintEngine;
import Program.System_Parts.Claster;
import Program.System_Parts.Initialize;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.codehaus.groovy.control.io.InputStreamReaderSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPrintTestEngine{
    @Test
    void normal(){
        PrintEngine printEngine = new PrintEngine(Initialize.InitializeClaster());

        printEngine.print();
    }
    @Test
    void serversNull(){
        Assertions.assertDoesNotThrow( () -> {
            PrintEngine printEngine = new PrintEngine(new Claster(null));
            printEngine.print();
        });
    }
    @Test
    void nodesNull(){
        Assertions.assertDoesNotThrow( () -> {
            SystPartOptional<Server> serv = new SystPartOptional<>(new Server(null));
            PrintEngine printEngine = new PrintEngine(new Claster(serv));

            printEngine.print();
        });
    }
}
