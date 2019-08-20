package testProgram.System_Parts;

import Program.System_Parts.Claster;
import Program.System_Parts.Initialize;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Test;
import testProgram.Wrappers.TestSystPartOptional;

import java.util.Random;

class TestInitialize {
   @Test
    void testInitializeClaster(){
        Claster claster = Initialize.InitializeClaster();
        assert (claster.isStatus());
    }
    @Test
    void ClasterInitializeWithoutNull(){
        Claster claster = Initialize.InitializeClaster();
        for(SystPartOptional<Server> i : claster.servers){
            assert (i.hasValue());

            for(SystPartOptional<Node> j : i.getValue().nodes){

                assert (j.hasValue());
            }
        }
    }
    @Test
    void uniquenessInitializeClasters(){
        Claster claster1 = Initialize.InitializeClaster();
        Claster claster2 = Initialize.InitializeClaster();

        for(int i = 0; i < claster1.servers.size(); i++){
            Server tempServ1 = claster1.servers.get(i).getValue();
            Server tempServ2 = claster2.servers.get(i).getValue();

            int tempIdSystPart1 = tempServ1.getId();
            int tempIdSystPart2 = tempServ2.getId();

            assert (tempIdSystPart1 != tempIdSystPart2);

            for(int j = 0; j < claster1.servers.get(i).getValue().nodes.size(); j++){
                Node node1 = tempServ1.nodes.get(j).getValue();
                Node node2 = tempServ2.nodes.get(j).getValue();

                tempIdSystPart1 = claster1.servers.get(i).getValue().nodes.get(j).getValue().getId();
                tempIdSystPart2 = claster2.servers.get(i).getValue().nodes.get(j).getValue().getId();

                assert (tempIdSystPart1 != tempIdSystPart2);
            }
        }
    }
}
