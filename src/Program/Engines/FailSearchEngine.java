package Program.Engines;

import Program.System_Parts.*;
import Program.Wrappers.SystPartOptional;

import java.util.ArrayList;

public class FailSearchEngine extends Engine {

    public FailSearchEngine(Claster claster) {
        super(claster);
    }

    public String search() {
        SystPartOptional<Server> serv = new SystPartOptional<>(null);
        SystPartOptional<Node> node =  new SystPartOptional<>(null);

        serv = findSystPart(claster.servers);

        if(serv.hasValue())
            node = findSystPart(serv.getValue().nodes);

        return createAnswer(serv, node);
    }

    private <T extends SystPartOptional> T findSystPart(ArrayList<T> parts) {
        parts = disregEmpty(parts);

        if(parts.size() == 0)
            return (T)new SystPartOptional(null);

        int position = parts.size() / 2;
        int half = parts.size() / 2;

        while (position > 0) {

            if (parts.get(position).getValue().isStatus()) {
                half /= 2;

                if (half != 0)
                    position += half;

                else {
                    position++;
                }
            }

            else {

                if (parts.get(position - 1).getValue().isStatus())
                    return parts.get(position);

                half /= 2;

                if (half != 0)
                    position -= half;

                else {
                    position--;
                }
            }
        }
        return parts.get(0);
    }

    private <T extends SystPartOptional> ArrayList<T>  disregEmpty(ArrayList<T> parts) {
        ArrayList<T> newParts = new ArrayList<>();

        if (parts != null) {

            for (int i = 0; i < parts.size(); i++) {

                    if (parts.get(i).hasValue()) {
                    newParts.add(parts.get(i));
                }
            }
        }
        return newParts;
    }

    private String createAnswer(SystPartOptional<Server> serv, SystPartOptional<Node> node) {

        return String.format("Signal stopped on " + (serv.hasValue() ? serv.getValue().getId() : "nowhere")
                           + " Server " + (node.hasValue() ? node.getValue().getId() : "nowhere") + " Node");
    }

}

