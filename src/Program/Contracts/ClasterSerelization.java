package Program.Contracts;

import Program.System_Parts.Claster;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.IOException;

public  interface  ClasterSerelization{
     void serelClast(Claster claster) throws Exception;
}
