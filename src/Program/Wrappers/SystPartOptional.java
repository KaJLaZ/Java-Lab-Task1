package Program.Wrappers;

import Program.System_Parts.SystemPart;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SystPartOptional<T extends SystemPart> {
    private T value;
    public T getValue() {
        checkNull(value, new myAction(){
            public void action(){
                throw new NullPointerException("Get null"); }});

        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SystPartOptional(T value){
         this.value = value;
    }

    private SystPartOptional(){};

    public boolean hasValue(){
        return value != null;
    }

    private void checkNull(T value, myAction action){
        if(value == null)
            action.action();
    }
    private interface myAction{
        void action();
    }
}




