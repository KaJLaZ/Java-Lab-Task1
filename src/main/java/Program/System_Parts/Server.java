package Program.System_Parts;

import Program.Wrappers.SystPartOptional;

import java.util.ArrayList;
import java.util.Collections;

public class Server extends SystemPart {

    private static final int PLACE_HIERARCHY = 100;

    public ArrayList<SystPartOptional<Node>> nodes;

    public Server(SystPartOptional<Node>... nodes) {
        this(PLACE_HIERARCHY, nodes);
    }

    protected Server(int placeHierarchy, SystPartOptional<Node>... nodes){
        super(placeHierarchy);

        this.nodes = new ArrayList<>();

        if(nodes != null)
            Collections.addAll(this.nodes, nodes);
    }

    @Override
    public boolean isStatus(){
        int workingNodes = 0;

        for(SystPartOptional<Node> node : nodes){

            if(node.hasValue()){

                if(node.getValue().isStatus())
                    workingNodes++;

                else{
                    workingNodes = 0;
                    break;
                }
            }
        }
        setStatus(workingNodes > 0);
        return super.isStatus();
    }
    @Override
    public boolean equals(Object obj){
        Server server;
        if(!(obj instanceof Server))
            return false;

        server = (Server) obj;

        if(!super.equals(server) || this.nodes.size() != server.nodes.size())
            return false;

        for (int i = 0; i < server.nodes.size(); i++) {
            if (nodes.get(i).hasValue() && !server.nodes.get(i).hasValue() ||
                    !nodes.get(i).hasValue() && server.nodes.get(i).hasValue())
                return false;

            if(nodes.get(i).hasValue()) {

                if(!nodes.get(i).getValue().equals(server.nodes.get(i).getValue()))
                    return false;
            }
        }
        return true;
    }
}
