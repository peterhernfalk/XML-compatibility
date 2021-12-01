/* Copyright 2012 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package wsdl;

import java.util.List;

import com.predic8.schema.Schema;
import com.predic8.wsdl.*;
import com.predic8.wsdl.diff.WsdlDiffGenerator;
import com.predic8.soamodel.Difference;

public class CompareWSDL {

  public static void compare(String originalFile, String modifiedFile){
    WSDLParser parser = new WSDLParser();

    Definitions wsdl1 = parser.parse(originalFile);
    Definitions wsdl2 = parser.parse(modifiedFile);
    Boolean backwardsCompatible = true;

    WsdlDiffGenerator diffGen = new WsdlDiffGenerator(wsdl1, wsdl2);
    List<Difference> lst = diffGen.compare();
    for (Difference diff : lst) {
    	System.out.println(diff.dump());
    	if (diff.dump().contains("moved") || diff.dump().contains("delete")) {
          backwardsCompatible = false;
        }
    }
    if (backwardsCompatible == true) {
      System.out.println("===== Resultat: bakåtkompaibel =====");
      System.out.println("====================================");
    } else if (backwardsCompatible == false) {
      System.out.println("===== Resultat: ändringar har gjorts mellan versionerna =====");
      System.out.println("=============================================================");
    }

  }

  private static void dumpDiff(Difference diff, String level) {
    System.out.println(level + diff.getDescription());
    for (Difference localDiff : diff.getDiffs()){
      dumpDiff(localDiff, level + "  ");
    }
  }
  
}
