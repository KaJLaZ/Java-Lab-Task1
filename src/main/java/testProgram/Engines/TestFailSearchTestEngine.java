package testProgram.Engines;

import Program.Engines.Engine;
import Program.Engines.FailSearchEngine;
import Program.Engines.PrintEngine;
import Program.System_Parts.Claster;
import Program.System_Parts.Initialize;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testProgram.System_Parts.TestNode;
import testProgram.System_Parts.TestServer;
import testProgram.Wrappers.TestSystPartOptional;

import java.util.ArrayList;

public class TestFailSearchTestEngine  {
    @Test
    void normal(){
        FailSearchEngine failSearchEngine = new FailSearchEngine(Initialize.InitializeClaster());

        assert (failSearchEngine.search().equals("Signal stopped on nowhere TestServer nowhere TestNode"));
    }
    @Test
    void serversNull(){
        Assertions.assertDoesNotThrow( () -> {
            FailSearchEngine failSearchEngine = new FailSearchEngine(new Claster(null));
            failSearchEngine.search();

            assert (failSearchEngine.search().equals("Signal stopped on nowhere TestServer nowhere TestNode"));
        });
    }
    @Test
    void nodesNull(){
        Assertions.assertDoesNotThrow( () -> {
            SystPartOptional<Server> serv = new SystPartOptional<>(new Server(null));
            FailSearchEngine failSearchEngine = new FailSearchEngine(new Claster(serv));
            String answer = String.format("Signal stopped on %d TestServer nowhere TestNode", serv.getValue().getId());

            assert (failSearchEngine.search().equals(answer));
        });
    }
}

