package Program.System_Parts;

import Program.Wrappers.SystPartOptional;

import java.util.Random;

public class Initialize {
    public static Claster InitializeClaster(){
        return new Claster(InitializeServer(), InitializeServer(), InitializeServer());
    }

    private static SystPartOptional<Server> InitializeServer(){
        return exist() ? new SystPartOptional<>(new Server(InitializeNode(), InitializeNode(), InitializeNode())) :
                         new SystPartOptional<>(null);
    }

    private static SystPartOptional<Node> InitializeNode(){
      return exist() ? new SystPartOptional<>(new Node()) : new SystPartOptional<>(null);
    }

    private static boolean exist(){
        return new Random().nextBoolean();
    }
}
