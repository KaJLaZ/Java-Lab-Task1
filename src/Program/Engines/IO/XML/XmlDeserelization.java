package Program.Engines.IO.XML;

import Program.Contracts.ClasterDeserelization;
import Program.System_Parts.Claster;
import Program.System_Parts.SystemPart;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.*;
import java.io.File;
import java.io.IOException;

public class XmlDeserelization implements ClasterDeserelization {
    XMLStreamReader xmlStrRe;
    XmlMapper mapper;

    public XmlDeserelization(XMLStreamReader xmlStrRe) {
        this.xmlStrRe = xmlStrRe;
        mapper = new XmlMapper();
    }

    @Override
    public Claster deserelClast() throws Exception {
        /*
        Claster newClaster = new Claster();
        XmlMapper mapper = new XmlMapper();


        needs refinement
        */
        throw new Exception("needs refinement");
    }

    private void deserelizSystPart(SystemPart systPart) throws IOException{
        systPart.setId(Integer.parseInt(xmlStrRe.getAttributeValue(0)));
        systPart.setStatus(Boolean.valueOf(xmlStrRe.getAttributeValue(1)));
    }
}
