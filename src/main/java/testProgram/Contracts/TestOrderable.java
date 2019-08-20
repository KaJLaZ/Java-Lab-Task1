package testProgram.Contracts;

import Program.Contracts.Orderable;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import org.junit.jupiter.api.Test;

class TestOrderable {
    @Test
    void checkGetIdHierarchy(){
        Orderable order1 = new Node();

        Orderable order2 = new Server();

        Orderable order3 = new Claster();

        assert (order3.getId() < order2.getId() && order2.getId() < order1.getId());
    }
    @Test
    void checkGetIdUnigness(){
        Orderable orderNode1 = new Node();
        Orderable orderNode2 = new Node();

        Orderable orderServ1 = new Server();
        Orderable orderServ2 = new Server();

        Orderable orderClast1 = new Claster();
        Orderable orderClast2 = new Claster();

        assert (orderNode1.getId() != orderNode2.getId() &&

                orderServ1.getId() != orderServ2.getId() &&

                orderClast1.getId() != orderClast2.getId() &&

                orderNode1.getId() != orderServ1.getId() &&

                orderServ2.getId() != orderClast1.getId() &&

                orderClast1.getId() != orderNode2.getId());
    }
    @Test
    void checkGetIdAntiUnigness(){
        Orderable orderNode = new Node();
        Orderable orderServ = new Server();
        Orderable orderClast = new Claster();

        assert (orderNode.getId() == orderNode.getId() &&

                orderServ.getId() == orderServ.getId() &&

                orderClast.getId() == orderClast.getId());
    }
    @Test
    void isWorkSetId(){
        Orderable order = new Node();
        int num = 12;

        order.setId(num);

        assert (num == order.getId());
    }

    @Test
    void notOverridingSetId(){
        Orderable order1 = new Node();
        Orderable order2 = new Node();
        int num = 12;

        order1.setId(num);
        order2.setId(num);

        assert (order1.getId() == order2.getId());
    }
    @Test
    void testSetId(){
        Orderable order1 = new Node();
        Orderable order2 = new Node();
        int num = 1;
        order1.setId(num);
        order2.setId(num + 1);

        assert (order1.getId() != order2.getId());
    }
}
