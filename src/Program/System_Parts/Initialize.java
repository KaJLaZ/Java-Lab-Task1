package Program.System_Parts;

public class Initialize {
    public static Claster InitializeClaster(){
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();

        Server s1 = new Server(n1, n2, n3);

        Node n4 = new Node();
        Node n5 = new Node();
        Node n6 = new Node();

        Server s2 = new Server(n4, n5, n6);

        Node n7 = new Node();
        Node n8 = new Node();
        Node n9 = new Node();

        Server s3 = new Server(n7, n8, n9);

        return new Claster(s1, s2, s3);
    }
}
