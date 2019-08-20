package testProgram.System_Parts;

import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import Program.Wrappers.SystPartOptional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestSystemPart {
    @Test
    void keepSystemParts() {
        SystemPart[] systemParts = new SystemPart[3];

        int ordNum = 0;
        systemParts[ordNum++] = new Node();
        systemParts[ordNum++] = new Server();
        systemParts[ordNum] = new Claster();

        for (int i = 1; i < systemParts.length; i++) {
            assert (systemParts[i].getId() < systemParts[i - 1].getId());
        }
    }
    @Test
    void setNull(){
        SystemPart systemPart = null;

        Assertions.assertThrows(NullPointerException.class, ()->{
            systemPart.getId();
        });
    }

    @Test
    void containSystemPartInsideOptional(){
        SystemPart systemPart = new Node();
        SystPartOptional<SystemPart> wrapSystPart = new SystPartOptional<>(systemPart);
        assert (systemPart == wrapSystPart.getValue());
    }

    @Nested
    class testEquals{
        @Test
        void normal(){
            SystemPart systemPart = new Node();

            assert(!systemPart.equals(new Node()));
        }
        @Test
        void sameNodes(){
            SystemPart systemPart = new Node();

            assert(systemPart.equals(systemPart));
        }
        @Test
        void difNodes(){
            SystemPart systemPart1 = new Node();

            SystemPart systemPart2 = new Node();

            assert(!systemPart1.equals(systemPart2));
        }
        @Test
        void sameId(){
            SystemPart systemPart1 = new Node();

            SystemPart systemPart2 = new Node();

            systemPart2.setId(systemPart1.getId());

            assert(systemPart1.equals(systemPart2));
        }
        @Test
        void difStatus(){
            SystemPart systemPart1 = new Node();

            SystemPart systemPart2 = new Node();

            systemPart2.setStatus(false);

            assert(!systemPart1.equals(systemPart2));
        }
        @Test
        void sameStatus(){
            SystemPart systemPart1 = new Node();
            SystemPart systemPart2 = new Node();

            systemPart1.setStatus(false);
            systemPart2.setStatus(false);

            assert(!systemPart1.equals(systemPart2));
        }
    }
}


