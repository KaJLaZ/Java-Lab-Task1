package Program.System_Parts;

import java.util.ArrayList;

 public class Server extends Claster {

    private static final int PLACE_HIERARCHY = 100;

    public ArrayList<Node> nodes;

    public Server(Node... servers) {
        this(PLACE_HIERARCHY, servers);
    }

    protected Server(int placeHierarchy, Node... nodes){
        super(placeHierarchy);

        this.nodes = new ArrayList<>();

        for(Node i : nodes)
            this.nodes.add(i);
    }
}
