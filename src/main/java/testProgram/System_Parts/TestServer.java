package testProgram.System_Parts;

import Program.Contracts.Orderable;
import Program.Contracts.Statuseable;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import testProgram.Wrappers.TestSystPartOptional;

import java.util.ArrayList;
import java.util.Collections;

public class TestServer extends TestSystemPart {
    Server serv = new Server(new SystPartOptional<>(new Node()));

    @Test
    void setNull () {
        Server Server = new Server(null);
    }

    @Nested
    class testIsStatus{
        @Test
        void posOvverIsStatus () {
            serv.isStatus();
        }
        @Test
        void negOvverIsStatus1 () {
            serv.nodes.clear();

            assert (!serv.isStatus());
        }
        @Test
        void negOvverIsStatus2 () {
            for (SystPartOptional<Node> i : serv.nodes)
                i.setNull();

            assert (!serv.isStatus());
        }
        @Test
        void firsPartFalseSeconTrue () {
            SystPartOptional<Node> node1 = new SystPartOptional<>(new Node());
            SystPartOptional<Node> node2 = new SystPartOptional<>(new Node());
            Server serv = new  Server(node1, node2);

            assert (serv.isStatus());

            node1.getValue().setStatus(false);
            node2.getValue().setStatus(true);

            assert (!serv.isStatus());
        }

        @Test
        void firsPartTrueSeconFalse () {
            SystPartOptional<Node> node1 = new SystPartOptional<>(new Node());
            SystPartOptional<Node> node2 = new SystPartOptional<>(new Node());
            Server serv = new Server(node1, node2);

            assert (serv.isStatus());

            node1.getValue().setStatus(true);
            node2.getValue().setStatus(false);

            assert (!serv.isStatus());
        }
    }

    @Nested
    class testEquals{
        @Test
        void sameServ(){
            SystPartOptional<Node> node = new SystPartOptional<>(new Node());
            Server serv = new Server(node);
            assert(serv.equals(serv));
        }
        @Test
        void difNode(){
            SystPartOptional<Node> node1 = new SystPartOptional<>(new Node());
            SystPartOptional<Node> node2 = new SystPartOptional<>(new Node());
            Server serv1 = new Server(node1);
            Server serv2 = new Server(node2);
            assert(!serv1.equals(serv2));
        }
        @Test
        void sameNode(){
            SystPartOptional<Node> node = new SystPartOptional<>(new Node());
            Server serv1 = new Server(node);
            Server serv2 = new Server(node);
            assert(!serv1.equals(serv2));
        }
    }
}
