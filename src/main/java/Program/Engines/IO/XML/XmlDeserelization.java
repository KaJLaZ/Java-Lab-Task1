package Program.Engines.IO.XML;

import Program.Contracts.ClasterDeserelization;
import Program.System_Parts.Claster;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import Program.Wrappers.SystPartOptional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;;
import java.io.IOException;


public class XmlDeserelization implements ClasterDeserelization {
    Document doc;

    public XmlDeserelization(Document doc) {
        this.doc = doc;
    }

    @Override
     public Claster deserelClast() throws Exception {
        Claster newClaster = new Claster();

        Node nodeClaster = doc.getElementsByTagName("TestClaster").item(0);
        setAttributes(newClaster, nodeClaster);

        NodeList servers = nodeClaster.getChildNodes();
        for(int i = 0; i < servers.getLength(); i++){
            newClaster.servers.add(new SystPartOptional<>(new Server()));
            deserelServ(newClaster.servers.get(i), servers.item(i));
        }
        return newClaster;
}

    private void deserelServ(SystPartOptional<Server> serv, Node node) throws Exception {
        setAttributes(serv.getValue(), node);

        NodeList nodes = node.getChildNodes();

        for(int i = 0; i < nodes.getLength(); i++){
            serv.getValue().nodes.add(new SystPartOptional<>(new Program.System_Parts.Node()));
            deserelNode(serv.getValue().nodes.get(i), nodes.item(i));
        }
    }

    private void deserelNode(SystPartOptional<Program.System_Parts.Node> systPartNode, Node node) throws Exception {
        setAttributes(systPartNode.getValue(), node);
    }

    private void setAttributes(SystemPart systPart, Node node) throws IOException{
        Node id = node.getAttributes().getNamedItem("id");
        Node status = node.getAttributes().getNamedItem("status");

        systPart.setId(Integer.parseInt(id.getNodeValue()));
        systPart.setStatus("true".equalsIgnoreCase(status.getNodeValue()));
    }
}
