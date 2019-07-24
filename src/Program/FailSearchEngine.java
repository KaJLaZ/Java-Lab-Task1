package Program;

import Program.System_Parts.*;

import java.util.ArrayList;

public class FailSearchEngine {
    Claster claster;

    public FailSearchEngine(Claster claster) {
        this.claster = claster;
    }

    public String search() {
        ArrayList<Node> nodes = new ArrayList<>();

        for (Server i : claster.servers)
                nodes.addAll(i.nodes);

        int position = nodes.size() / 2;
        int half = nodes.size() /2;

        while(position > 0){

            if(nodes.get(position).isStatus()){
                half /= 2;

                if(half != 0)
                    position += half;

                else{ position++;}
            }

            else{

                if(nodes.get(position - 1).isStatus())
                    return createAnswer(nodes.get(position));

                half /= 2;

                if(half != 0)
                    position -= half;

                else{ position--;}
            }
        }
        return createAnswer(nodes.get(0));
    }

    private String createAnswer(Node node){
        return String.format("Signal stoped on " + findServ(node).getId() + " Server " + node.getId() + " Node");
    }

    private Server findServ(Node node) {

        for (Server i : claster.servers) {

            if (i.nodes.contains(node))
                return i;
        }

        return null;
    }

}



