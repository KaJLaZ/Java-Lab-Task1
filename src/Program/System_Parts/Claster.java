package Program.System_Parts;

import Program.Wrappers.SystPartOptional;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Claster extends SystemPart {

    private static final int PLACE_HIERARCHY = 1;

    public ArrayList<SystPartOptional<Server>> servers;

    public Claster(SystPartOptional<Server>... servers){
        this(PLACE_HIERARCHY, servers);
    }

    protected Claster(int placeHierarchy, SystPartOptional<Server>... servers){
        super(placeHierarchy);

        this.servers = new ArrayList<>();

        Collections.addAll(this.servers, servers);
    }

    private Claster(){};

    public void sendMessage(){
        setStatus(false);

        if(!existSystemPart())
                return;

        int i = beginGupSystPart(servers);

        if(!servers.get(i).hasValue())
            return;

        int j = beginGupSystPart(servers.get(i).getValue().nodes);

        for( ; i < servers.size(); i++){

            if(!servers.get(i).hasValue())
                continue;

            servers.get(i).getValue().setStatus(false);

            for(; j < servers.get(i).getValue().nodes.size(); j++){

                if(!servers.get(i).getValue().nodes.get(j).hasValue())
                    continue;

                servers.get(i).getValue().nodes.get(j).getValue().setStatus(false);
            }
            j = 0;
        }

    }

    private boolean existSystemPart(){
         return SystemPart.amount > 0;
    }

    private <T extends SystemPart> int beginGupSystPart(ArrayList<SystPartOptional<T>> parts){
        Random rand = new Random();
        int numSer = rand.nextInt(parts.size());

        for(int i = numSer; i < parts.size(); i++){
            if(parts.get(i).hasValue())
                return i;
        }

        for(int i = 0; i < numSer; i++){
            if(parts.get(i).hasValue())
                return i;
        }

        return numSer;
    }

    public boolean isFailed(int servId, int nodeId){
         try {
             SystPartOptional<Node> temp = FindNode(servId, nodeId);
         }
         catch (findException ex){
             System.out.println(ex.getMessage());
             return false;
         }

        return FindNode(servId, nodeId).getValue().isStatus();
    }

    private SystPartOptional<Node> FindNode(int servId, int nodeId){

        for(SystPartOptional<Server> i : servers){

            if(!i.hasValue())
                continue;

            if(i.getValue().getId() == servId){

                for(SystPartOptional<Node> j : i.getValue().nodes){

                    if(!j.hasValue())
                        continue;

                    if(j.getValue().getId() == nodeId)
                        return j;
                }
            }
        }
        throw new findException("didn't find Node");
    }

    class findException extends RuntimeException{
        public findException(String message) {
            super(message);
        }
    }
}
