package Program.System_Parts;

import Program.Contracts.Orderable;
import Program.Contracts.Statuseable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

public abstract class SystemPart implements Orderable, Statuseable {
    @Override
    public int getId() {
        return id;
    }
    @Override
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


    public SystemPart(int placeHierarchy){
        id = amount++ + placeHierarchy;

        status = true;
    }

    protected SystemPart(){ }
}
