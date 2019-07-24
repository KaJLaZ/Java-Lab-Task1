package Program.System_Parts;

import Program.Contracts.SystemPart;

import java.util.ArrayList;
import java.util.Random;

public class Claster extends SystemPart {

    private static final int PLACE_HIERARCHY = 1;

    public ArrayList<Server> servers;

     public Claster(Server... servers){
        this(PLACE_HIERARCHY, servers);
    }

    protected Claster(int placeHierarchy,Server... servers){
        super(placeHierarchy);

        this.servers = new ArrayList<>();

        for(Server i : servers)
            this.servers.add(i);
    }

    public void sendMessage(){
        setStatus(false);

        Random rand = new Random();
        int i = rand.nextInt(servers.size());
        int j = rand.nextInt(servers.get(i).nodes.size());

        for( ; i < servers.size(); i++){
            servers.get(i).setStatus(false);

            for(; j < servers.get(i).nodes.size(); j++){

                servers.get(i).nodes.get(j).setStatus(false);
            }
            j = 0;
        }

    }

    public boolean isFailed(int servId, int nodeId){
        Node node = FindNode(servId, nodeId);

        return node == null ? false : node.isStatus();


    }

    private Node FindNode(int servId, int nodeId){
        for(Server i : servers){

            if(i.getId() == servId){

                for(Node j : i.nodes){

                    if(j.getId() == nodeId)
                        return j;
                }
            }
        }
        return null;
    }
}
