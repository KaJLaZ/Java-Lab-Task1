package Program.Engines.IO;

import Program.Contracts.ClasterDeserelization;
import Program.Contracts.ClasterSerelization;
import Program.Engines.Engine;
import Program.Engines.IO.JSON.JsonDeserilization;
import Program.Engines.IO.JSON.JsonSerelization;
import Program.Engines.IO.XML.XmlDeserelization;
import Program.Engines.IO.XML.XmlSerelization;
import Program.System_Parts.Claster;
import com.fasterxml.jackson.core.*;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;

public class IOEngine extends Engine{
    public enum Formats {
        JSON,
        XML
    }

    Formats format;

    private ClasterSerelization getSerelization(File place) throws Exception{
        switch (format){
            case XML:
                XMLStreamWriter xmlStrWr = XMLOutputFactory.newFactory().createXMLStreamWriter(new FileWriter(place));
                return new XmlSerelization(xmlStrWr);

                default:
                    JsonGenerator gen = new JsonFactory().createGenerator(place, JsonEncoding.UTF8);
                    return new JsonSerelization(gen);
        }
    }

    private ClasterDeserelization getDeserelization(File place) throws Exception{
        switch (format){
            case XML:
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(place);
                doc.getDocumentElement().normalize();
                return new XmlDeserelization(doc);

            default:
                JsonParser jPar = new JsonFactory().createParser(place);
                return new JsonDeserilization(jPar);
        }
    }

    public IOEngine(Claster claster, Formats format) {
        super(claster);
        this.format = format;
    }

    public void save(File place, Claster claster){
        this.claster = claster;

        try{
            ClasterSerelization serelization = getSerelization(place);
            serelization.serelClast(claster);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + "\n incorrect path");
        }
    }

    public void save(File place){
        save(place, claster);
    }

    public Claster load(File place) {
        Claster newClaster = new Claster();

        try {
            ClasterDeserelization deserelization = getDeserelization(place);
            newClaster = deserelization.deserelClast();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + "\n incorrect path");
        }

        this.claster = newClaster;
        return newClaster;
    }
}