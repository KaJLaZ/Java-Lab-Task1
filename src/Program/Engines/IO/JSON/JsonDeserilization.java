package Program.Engines.IO.JSON;

import Program.Contracts.ClasterDeserelization;
import Program.System_Parts.*;
import Program.Wrappers.SystPartOptional;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

 public class JsonDeserilization implements ClasterDeserelization {
     JsonParser jsPars;

     public JsonDeserilization(JsonParser jsPars) {
         this.jsPars = jsPars;
     }

     @Override
     public Claster deserelClast() throws IOException {
         Claster claster = new Claster();

         jsPars.nextToken();
         deserelizSystPart(claster);
         jsPars.nextToken();

         for(int i = 0;jsPars.currentToken() == JsonToken.START_OBJECT; i++){
             claster.servers.add(new SystPartOptional<>(new Server()));
             deserelServer(claster.servers.get(i));

             jsPars.nextToken();
             jsPars.nextToken();
         }
        return claster;
    }

    public void deserelServer(SystPartOptional<Server> serv) throws IOException{
        deserelizSystPart(serv.getValue());
        jsPars.nextToken();

        for(int i = 0; jsPars.currentToken() == JsonToken.START_OBJECT; i++){
            serv.getValue().nodes.add(new SystPartOptional<>(new Node()));
            deserelNode(serv.getValue().nodes.get(i));
        }
    }

    private void deserelNode(SystPartOptional<Node> node) throws IOException{
        deserelizSystPart(node.getValue());
    }

    private void deserelizSystPart(SystemPart systPart) throws IOException{
         jsPars.nextToken();
         jsPars.nextToken();

         systPart.setId(jsPars.getIntValue());

         jsPars.nextToken();
         jsPars.nextToken();

         systPart.setStatus(jsPars.getBooleanValue());

         jsPars.nextToken();
         jsPars.nextToken();
     }
}
