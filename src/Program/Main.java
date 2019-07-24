package Program;

import Program.System_Parts.*;

public class Main {
    public static void main(String[] args) {

        Claster claster = Initialize.InitializeClaster();
        FailSearchEngine failSearchEngine = new FailSearchEngine(claster);

        claster.sendMessage();
        System.out.println(failSearchEngine.search());



    }
}


