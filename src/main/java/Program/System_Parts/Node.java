package Program.System_Parts;


public class Node extends SystemPart {

    private static final int PLACE_HIERARCHY = 10000;

    public Node(){
            super(PLACE_HIERARCHY);
    }

    @Override
    public boolean equals(Object obj){
        Node node;

        if(!(obj instanceof Node))
            return false;

        node = (Node) obj;

        return super.equals(node);
    }
}
