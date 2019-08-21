package Program.System_Parts;

import Program.Contracts.Orderable;
import Program.Contracts.Statuseable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public abstract class SystemPart implements Orderable, Statuseable {

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    private int id;

    protected static int amount = 0;

    @Override
    public boolean isStatus() {
        return status;
    }
    @Override
    public void setStatus(boolean status){this.status = status;}
    private boolean status;

    @Override
    public boolean equals(Object obj){
        SystemPart claster;

        if(!(obj instanceof SystemPart))
            return false;

        claster = (SystemPart) obj;

        if(this.getId() != claster.getId())
            return false;

        return true;
        }

    public SystemPart(int placeHierarchy){
        id = amount++ + placeHierarchy;

        status = true;
    }

    protected SystemPart(){ }
}
