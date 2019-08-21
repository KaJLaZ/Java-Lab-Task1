package Program.System_Parts;

import Program.Wrappers.SystPartOptional;

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

        if(servers != null)
            Collections.addAll(this.servers, servers);
    }

    @Override
    public boolean equals(Object obj) {
        Claster claster;
        if (!(obj instanceof Claster))
            return false;

        claster = (Claster) obj;

        if (!super.equals(claster) || servers.size() != claster.servers.size())
            return false;

        for (int i = 0; i < claster.servers.size(); i++) {
            if (servers.get(i).hasValue() && !claster.servers.get(i).hasValue() ||
                    !servers.get(i).hasValue() && claster.servers.get(i).hasValue())
                return false;

            if(servers.get(i).hasValue()) {

                if(!servers.get(i).getValue().equals(claster.servers.get(i).getValue()))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean isStatus(){
        int workingServers = 0;

        for(SystPartOptional<Server> serv : servers){

            if(serv.hasValue()){

                if(serv.getValue().isStatus())
                    workingServers++;

                else{
                    workingServers = 0;
                    break;
                }
            }
        }
        setStatus(workingServers > 0);
        return super.isStatus();
    }

    private Claster(){};

    public void sendMessage(){
        setStatus(false);

        if(servers.isEmpty())
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
}
