package Program.Contracts;

import Program.System_Parts.Claster;
import com.fasterxml.jackson.core.JsonParser;

import java.io.File;
import java.io.IOException;

public interface ClasterDeserelization {
    Claster deserelClast() throws Exception;
}
