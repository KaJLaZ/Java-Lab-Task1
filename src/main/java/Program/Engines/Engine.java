package Program.Engines;

import Program.System_Parts.Claster;

public abstract class Engine {
    protected Claster claster;

    public Engine(Claster claster) {
        if (claster == null)
            throw new NullPointerException(String.format("%s is null", claster.getClass().getName()));

        this.claster = claster;
    }
}
