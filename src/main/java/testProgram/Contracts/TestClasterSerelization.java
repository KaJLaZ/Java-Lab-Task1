package testProgram.Contracts;

import Program.Engines.IO.IOEngine;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;

class TestClasterSerelization {
    Claster claster = new Claster(new SystPartOptional<>(new Server(new SystPartOptional<>(new Node()))));
    IOEngine ioEngine = new IOEngine(claster, IOEngine.Formats.JSON);

    @Test
    void testSerelClast() {
        Claster tempclaster = new Claster();

        ioEngine.save(new File("test.json"));
        tempclaster = ioEngine.load(new File("test.json"));

        assert (claster.getId() == tempclaster.getId());
    }
    @Test
    void testNullPath() {
        Assertions.assertDoesNotThrow(()-> ioEngine.save(null));
    }

    @Test
    void deserIncFile() {
        IOEngine ioEngine = new IOEngine(claster, IOEngine.Formats.XML);
        Claster tempclaster = new Claster();

        ioEngine.save(new File("test.xml"));
        tempclaster = this.ioEngine.load(new File("test.xml"));

        assert (!tempclaster.isStatus());
    }
}
