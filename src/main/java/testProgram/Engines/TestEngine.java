package testProgram.Engines;

import Program.Engines.Engine;
import Program.Engines.FailSearchEngine;
import Program.Engines.IO.IOEngine;
import Program.Engines.PrintEngine;
import Program.System_Parts.Claster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

class TestEngine {
    @Test
    void createEngine(){
        File file = new File("test.json");

        Claster claster = new Claster();
        IOEngine engine = new IOEngine(claster, IOEngine.Formats.JSON);

        engine.save(file);

        assert (engine.load(file).equals(claster));
    }
    @Test
    void setNull(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            new PrintEngine(null);
        });
    }
    @Test
    void containNullinsideClas(){
        Assertions.assertDoesNotThrow(() -> {
            Engine engine = new PrintEngine(new Claster(null));
        });
    }
}
