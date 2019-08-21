package Program.System_Parts;

import Program.Wrappers.SystPartOptional;

import java.util.Random;

abstract public class Initialize {
    public static Claster InitializeClaster(){
            return new Claster(InitializeServer(), InitializeServer(), InitializeServer());
    }

    private static SystPartOptional<Server> InitializeServer(){
        try {
            return exist() ? new SystPartOptional<>(new Server(InitializeNode(), InitializeNode(), InitializeNode())) :
                    new SystPartOptional<>(null);
        }
        catch (NullPointerException ex){
            return InitializeServer();
        }
    }

    private static SystPartOptional<Node> InitializeNode(){
        try {
            return exist() ? new SystPartOptional<>(new Node()) : new SystPartOptional<>(null);
        }
        catch (NullPointerException ex){
            return InitializeNode();
        }
    }

    private static boolean exist(){
        return new Random().nextBoolean();
    }
}
