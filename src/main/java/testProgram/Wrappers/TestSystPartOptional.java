package testProgram.Wrappers;

import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import Program.Wrappers.SystPartOptional;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSystPartOptional<T extends SystemPart> {
    SystPartOptional<Node> systPart = new SystPartOptional<>(new Node());
    @Test
    void posTestHasValue() {
    assert systPart.hasValue();
}
    @Test
    void HasEmptyValue() {
        systPart.setNull();
        assert(!systPart.hasValue());
    }

    @Test
    void HasEmptyVal() {
        systPart.setNull();
        systPart = new SystPartOptional<>(new Node());

        Assertions.assertDoesNotThrow( () -> {
            assert (systPart.hasValue());
            systPart.getValue();
        });
    }

    @Test
    void nullTestSetValue() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            SystPartOptional<Node> systPart = new SystPartOptional<>(null);
        });
    }
    @Test
    void TestGetValue() {
        Node node = new Node();
        SystPartOptional<Node> systPart = new SystPartOptional<>(node);

        assert (systPart.getValue() == node);
    }
    @Test
    void setNull() {
        Assertions.assertDoesNotThrow( () -> {
            systPart.setNull();
        });
    }
    @Test
    void testGetNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            systPart.setNull();
            systPart.getValue();
        });
    }
    @Test
    void canContainAnySystePart() {
       SystPartOptional n = new SystPartOptional<>(new Node());
       SystPartOptional s = new SystPartOptional<>(new Server());
        n = s;
        n.getValue();
    }
}





