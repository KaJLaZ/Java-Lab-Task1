package testProgram.System_Parts;

import Program.System_Parts.Claster;
import Program.System_Parts.Initialize;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.*;
import testProgram.Wrappers.TestSystPartOptional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestClaster extends TestSystemPart {
    Claster claster;
    @BeforeEach
    void initializeTestClaster(){
         claster = Initialize.InitializeClaster();
    }

       @Nested
        class testIsStatus{
        @Test
        void posOvverIsStatus () {
            claster.isStatus();
        }
        @Test
        void negOvverIsStatus1 () {
            claster.servers.clear();

            assert (!claster.isStatus());
        }
           @Test
           void setNull () {
               Claster claster = new Claster(null);
           }
        @Test
        void negOvverIsStatus2 () {
            for (SystPartOptional<Server> i : claster.servers)
                i.setNull();

            assert (!claster.isStatus());
        }
        @Test
        void firsPartFalseSeconTrue () {
            SystPartOptional<Node> node1 = new SystPartOptional<>(new Node());
            SystPartOptional<Node> node2 = new SystPartOptional<>(new Node());
            SystPartOptional<Server> serv1 = new SystPartOptional<>(new Server(node1));
            SystPartOptional<Server> serv2 = new SystPartOptional<>(new Server(node2));
            Claster claster = new Claster(serv1, serv2);

            assert (claster.isStatus());

            node1.getValue().setStatus(false);
            serv1.getValue().setStatus(false);
            node2.getValue().setStatus(true);
            serv2.getValue().setStatus(true);

            assert (!claster.isStatus());
        }

           @Test
           void firsPartTrueSeconFalse () {
               SystPartOptional<Node> node1 = new SystPartOptional<>(new Node());
               SystPartOptional<Node> node2 = new SystPartOptional<>(new Node());
               SystPartOptional<Server> serv1 = new SystPartOptional<>(new Server(node1));
               SystPartOptional<Server> serv2 = new SystPartOptional<>(new Server(node2));
               Claster claster = new Claster(serv1, serv2);

               assert (claster.isStatus());

               node1.getValue().setStatus(true);
               serv1.getValue().setStatus(true);
               node2.getValue().setStatus(false);
               serv2.getValue().setStatus(false);

               assert (!claster.isStatus());
           }
    }
    @Nested
    class testSendMessage{
        @Test
        void work(){
            claster.sendMessage();

            assert (!claster.isStatus());
        }
        @Test
        void allPartsAfterFailFalse(){
            Boolean servStatus = true;
            Boolean nodeStatus = true;

            claster.sendMessage();

            for (SystPartOptional<Server> i : claster.servers){

                if(i.getValue().isStatus() != servStatus)
                    servStatus = false;

                assert (i.getValue().isStatus() == servStatus);

                for (SystPartOptional<Node> j : i.getValue().nodes){

                    if(j.getValue().isStatus() != nodeStatus)
                        nodeStatus = false;

                    assert (j.getValue().isStatus() == nodeStatus);
                }
            }
        }
        @Test
        void allServersNull(){
            for(SystPartOptional<Server> i : claster.servers)
                i.setNull();

            Assertions.assertDoesNotThrow( ()->{
                claster.sendMessage();
            });
        }

        @Test
        void dontHaveServers(){
            claster.servers.clear();

            Assertions.assertDoesNotThrow( ()->{
                claster.sendMessage();
            });
        }
    }

    @Nested
    class testEquals{
        @Test
        void sameClaster(){
            Claster claster = Initialize.InitializeClaster();

            assert(claster.equals(claster));
        }
        @Test
        void sameServerNode(){
            SystPartOptional<Node> node = new SystPartOptional<>(new Node());
            SystPartOptional<Server> serv = new SystPartOptional<>(new Server(node));

            Claster claster1 = new Claster(serv);
            Claster claster2 = new Claster(serv);

            assert(!claster1.equals(claster2));
        }
        @Test
        void difServer(){
            SystPartOptional<Node> node1 = new SystPartOptional<>(new Node());
            SystPartOptional<Node> node2 = new SystPartOptional<>(new Node());
            SystPartOptional<Server> serv1 = new SystPartOptional<>(new Server(node1));
            SystPartOptional<Server> serv2 = new SystPartOptional<>(new Server(node2));

            Claster claster1 = new Claster(serv1);
            Claster claster2 = new Claster(serv2);

            assert(!claster1.equals(claster2));
        }
    }
}
