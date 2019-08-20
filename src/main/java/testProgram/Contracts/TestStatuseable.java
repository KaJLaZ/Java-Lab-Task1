package testProgram.Contracts;

import Program.Contracts.Statuseable;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Test;


class TestStatuseable {
    @Test
    void testIsIstatus(){
        Statuseable stat1 = new Node();
        Statuseable stat2 = new Server();
        Statuseable stat3 = new Claster();

        assert (stat1.isStatus() && !stat2.isStatus() && !stat3.isStatus());
    }

    @Test
    void nullSystemPart(){
        SystPartOptional<Server> server = new SystPartOptional<>(new Server());
        SystPartOptional<Claster> claster = new SystPartOptional<>(new Claster());

        claster.getValue().servers.clear();
        server.getValue().nodes.clear();

        assert (!claster.getValue().isStatus() && !server.getValue().isStatus());
    }

    @Test
    void statAftBreak(){
        Claster claster = new Claster();

        claster.sendMessage();

        assert (!claster.isStatus());
    }

    @Test
    void negTestSetStatusHierarchy(){
        Server serv = new Server(new SystPartOptional<>(new Node()));
        Node node = serv.nodes.get(0).getValue();

        node.setStatus(false);

        assert (!serv.nodes.get(0).getValue().isStatus());
    }

    @Test
    void posTestSetStatusHierarchy(){
        Server serv = new Server(new SystPartOptional<>(new Node()));

        serv.setStatus(false);

        assert (serv.nodes.get(0).getValue().isStatus());
    }

    @Test
    void TestSetStatusHierarchy(){
        Server serv = new Server(new SystPartOptional<>(new Node()));
        Node node = serv.nodes.get(0).getValue();

        serv.setStatus(false);
        node.setStatus(true);

        assert (serv.isStatus());
    }
}
