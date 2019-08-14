package Program.Engines.IO.XML;

import Program.Contracts.ClasterSerelization;
import Program.System_Parts.Claster;
import Program.System_Parts.Node;
import Program.System_Parts.Server;
import Program.System_Parts.SystemPart;
import Program.Wrappers.SystPartOptional;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamWriter;

public class XmlSerelization implements ClasterSerelization {
    XMLStreamWriter xmlStrWr;
    XmlMapper mapper;

    public XmlSerelization(XMLStreamWriter xmlStrWr) {
        this.xmlStrWr = xmlStrWr;
        mapper = new XmlMapper(XMLInputFactory.newFactory());
    }

    @Override
    public void serelClast(Claster claster) throws Exception {
            xmlStrWr.writeStartDocument();
            xmlStrWr.writeStartElement("Claster");
            serelizSystPart(claster);

            mapper.writeValue(xmlStrWr, "Servers");

            for(SystPartOptional<Server> i : claster.servers){
                if(i.hasValue())
                    serelServer(i.getValue());
            }

            xmlStrWr.writeEndElement();
            xmlStrWr.writeEndDocument();
            xmlStrWr.close();
    }

    private void serelServer(Server server) throws Exception {
        xmlStrWr.writeStartElement("Server");
        serelizSystPart(server);

        mapper.writeValue(xmlStrWr, "Nodes");

        for(SystPartOptional<Node> i : server.nodes){
            if(i.hasValue())
                serelNode(i.getValue());
        }

        xmlStrWr.writeEndElement();
    }

    private void serelNode(Node node) throws Exception {
        xmlStrWr.writeStartElement("Node");
        serelizSystPart(node);
        xmlStrWr.writeEndElement();
    }

    private void serelizSystPart(SystemPart systPart) throws Exception{
        xmlStrWr.writeAttribute("id", Integer.toString(systPart.getId()));
        xmlStrWr.writeAttribute("status", Boolean.toString(systPart.isStatus()));
    }
}
