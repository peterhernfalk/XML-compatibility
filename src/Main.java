import rivta.compatibility.schema.CompareSchema;
import wsdl.CompareWSDL;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String originalFile = "";
        String modifiedFile = "";
        if (args.length == 2) {
            originalFile = args[0];
            modifiedFile = args[1];
        } else {
            System.out.println("Input parameters not correct. \nThe syntax is: java -jar XML-compatibility.jar <path-to-original-wsdl_or_xsd-file> <path-to-modified-wsdl_or_xsd-file>");
        }

        if (originalFile.toLowerCase().contains(".wsdl")) {
            CompareWSDL.compare(originalFile, modifiedFile);
        } else if (originalFile.toLowerCase().contains(".xsd")) {
            CompareSchema.compare(originalFile, modifiedFile);
        }
    }
}
