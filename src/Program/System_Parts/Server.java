package Program.System_Parts;

import Program.Wrappers.SystPartOptional;

import java.util.ArrayList;
import java.util.Collections;

public class Server extends SystemPart {

    private static final int PLACE_HIERARCHY = 100;

    public ArrayList<SystPartOptional<Node>> nodes;

    public Server(SystPartOptional<Node>... servers) {
        this(PLACE_HIERARCHY, servers);
    }

    protected Server(int placeHierarchy, SystPartOptional<Node>... nodes){
        super(placeHierarchy);

        this.nodes = new ArrayList<>();

        Collections.addAll(this.nodes, nodes);
    }
}
