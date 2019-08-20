package testProgram.System_Parts;


import Program.Contracts.Orderable;
import Program.Contracts.Statuseable;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestNode extends TestSystemPart {
    @Test
    void createNode () {
        Node node = new Node();
    }

    @Test
    void isEqual(){
        Node node = new Node();

       assert (node.equals(node));
    }
}
