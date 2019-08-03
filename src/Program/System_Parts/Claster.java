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
        return FindNode(servId, nodeId).isStatus();


    }

    private Node FindNode(int servId, int nodeId){

        class findException extends RuntimeException{
            public findException() {
            }

            public findException(String message) {
                super(message);
            }

            public findException(String message, Throwable cause) {
                super(message, cause);
            }

            public findException(Throwable cause) {
                super(cause);
            }

            public findException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
                super(message, cause, enableSuppression, writableStackTrace);
            }
        }

        for(Server i : servers){

            if(i.getId() == servId){

                for(Node j : i.nodes){

                    if(j.getId() == nodeId)
                        return j;
                }
            }
        }
        throw new findException("didn't find Node");


    }
}
