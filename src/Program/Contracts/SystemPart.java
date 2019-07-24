package Program.Contracts;

public abstract class SystemPart {

    public int getId() {
        return id;
    }
    private int id;

    private static int amount = 0;

    public boolean isStatus() {
        return status;
    }
    protected void setStatus(boolean status){this.status = status;}
    private boolean status;


    public SystemPart(int placeHierarchy){
        id = amount++ + placeHierarchy;

        status = true;
    }
}
