package Program.Engines.IO.JSON;

import Program.Contracts.ClasterSerelization;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import Program.Wrappers.SystPartOptional;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;

 public class JsonSerelization implements ClasterSerelization {
     JsonGenerator gen;

     public JsonSerelization(JsonGenerator gen) {
         this.gen = gen;
     }

     @Override
    public void serelClast(Claster claster) throws IOException {
        gen.writeStartObject();
        serelizSystPart(claster);

        gen.writeFieldName("servers");
        gen.writeStartArray();

        for(SystPartOptional<Server> i : claster.servers) {

            if (i.hasValue())
                serelServer(i.getValue());
        }

        gen.writeEndArray();
        gen.writeEndObject();
        gen.close();
    }

    private void serelServer(Server serv) throws IOException{
        gen.writeStartObject();
        serelizSystPart(serv);

        gen.writeFieldName("nodes");
        gen.writeStartArray();

        for(SystPartOptional<Node> i : serv.nodes){

            if(i.hasValue())
                serelNode(i);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

    private void serelNode(SystPartOptional<Node> node) throws IOException{
        gen.writeStartObject();
        serelizSystPart(node.getValue());
        gen.writeEndObject();
    }

    private void serelizSystPart(SystemPart systPart) throws IOException{
        gen.writeFieldName("id");
        gen.writeNumber(systPart.getId());

        gen.writeBooleanField("status", systPart.isStatus());
    }
}
