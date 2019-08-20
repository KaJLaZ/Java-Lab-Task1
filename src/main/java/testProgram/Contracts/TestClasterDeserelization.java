package testProgram.Contracts;

import Program.Engines.IO.IOEngine;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class TestClasterDeserelization {
    Claster claster = new Claster(new SystPartOptional<>(new Server(new SystPartOptional<>(new Node()))));
    IOEngine ioEngine = new IOEngine(claster, IOEngine.Formats.JSON);

    @BeforeEach
    void saveTestData(){
        ioEngine.save(new File("test.json"));
    }

    @Test
    void testIncorPath() {
        Claster temoclaster = ioEngine.load(new File("incorect path"));

        temoclaster.isStatus();

        assert (!temoclaster.isStatus());
    }

    @Test
    void testCorPath() {
        Claster claster = new Claster();

        claster = ioEngine.load(new File("test.json"));

        assert (claster.isStatus());
    }
    @Test
    void testNullPath() {
        Assertions.assertDoesNotThrow(()-> claster = ioEngine.load(null));
    }
}