package Program.Engines;

import Program.System_Parts.Claster;
import Program.System_Parts.Server;
import Program.Wrappers.SystPartOptional;

import java.util.ArrayList;

public class PrintEngine extends Engine {

    public PrintEngine(Claster claster) {
        super(claster);
    }

    public void print(){

        System.out.println(String.format(" Claster id: %d\n", claster.getId()));

            printSysPart(claster.servers, "Server");

        for(SystPartOptional<Server> i : claster.servers){

            if(i.hasValue()){

                printSysPart(i.getValue().nodes, "Node");
            }
        }
    }

    private <T extends SystPartOptional> void printSysPart(ArrayList<T> parts, String partName){
        if(parts == null)
            return;

        for(T i : parts)
            System.out.print(String.format(" %s id: %s ", partName,
                             (i.hasValue() ? i.getValue().getId() + " status: " + i.getValue().isStatus() : "null")));

        System.out.println("\n");
    }
}
