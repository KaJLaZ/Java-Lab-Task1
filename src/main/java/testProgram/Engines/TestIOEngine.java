package testProgram.Engines;

import Program.Engines.FailSearchEngine;
import Program.Engines.IO.IOEngine;
import Program.System_Parts.Claster;
import Program.System_Parts.Initialize;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testProgram.Engines.TestEngine;

import java.io.File;

public class TestIOEngine{
    File jsonPlace = new File("test.json");
    File xmlPlace = new File("test.xml");
    IOEngine ioEngine = new IOEngine(Initialize.InitializeClaster(), IOEngine.Formats.JSON);
    @Test
    void saveXmlLoadJson (){
        IOEngine ioEngine2 = new IOEngine(Initialize.InitializeClaster(), IOEngine.Formats.XML);

        ioEngine2.save(jsonPlace);
        Assertions.assertDoesNotThrow(()->{
            ioEngine.load(jsonPlace);
        });
    }
    @Test
    void saveJsonLoadXml (){
        IOEngine ioEngine2 = new IOEngine(Initialize.InitializeClaster(), IOEngine.Formats.XML);

        ioEngine.save(jsonPlace);
        Assertions.assertDoesNotThrow(()->{
            ioEngine2.load(jsonPlace);
        });
    }
    @Test
    void normal(){
        Claster claster = Initialize.InitializeClaster();
        assert (standartTest(claster));
    }
    @Test
    void serversNull(){
        Claster claster = new Claster(null);
        assert (standartTest(claster));
    }
    @Test
    void nodesNull(){
        SystPartOptional<Server> serv = new SystPartOptional<>(new Server(null));
        Claster claster = new Claster(serv);

        assert standartTest(claster);
    }

   private boolean standartTest(Claster claster){
        IOEngine jsonEngine = new IOEngine(claster, IOEngine.Formats.JSON);
        IOEngine xmlEngine = new IOEngine(claster, IOEngine.Formats.XML);

        jsonEngine.save(jsonPlace);
        xmlEngine.save(xmlPlace);

        return jsonEngine.load(jsonPlace).equals(claster);
    }
}
