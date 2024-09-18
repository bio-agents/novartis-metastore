package org.isaagents.novartismetastore.utils;

import org.isaagents.isacreator.ontologymanager.OntologySourceRefObject;
import org.isaagents.isacreator.ontologymanager.common.OntologyTerm;
import org.isaagents.novartismetastore.MetastoreClient;
import org.isaagents.novartismetastore.resource.MetastoreResult;
import org.isaagents.novartismetastore.resource.ResourceDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 03/10/2011
 *         Time: 17:04
 */
public class Convert {

    public static Map<OntologySourceRefObject, List<OntologyTerm>> convertMetastoreResult(List<MetastoreResult> metastoreResults, ResourceDescription resourceDescription) {
        Map<OntologySourceRefObject, List<OntologyTerm>> convertedResult = new HashMap<OntologySourceRefObject, List<OntologyTerm>>();

        OntologySourceRefObject source = new OntologySourceRefObject(
                resourceDescription.getResourceAbbreviation(), "", resourceDescription.getResourceVersion(), resourceDescription.getResourceName());

        convertedResult.put(source, new ArrayList<OntologyTerm>());

        for(MetastoreResult result : metastoreResults) {
            OntologyTerm ontologyTerm = new OntologyTerm(result.getToken(), result.getId(), source);
            ontologyTerm.addToComments("Species", result.getSpecies() == null ? "" : result.getSpecies());
            convertedResult.get(source).add(ontologyTerm);
        }

        return convertedResult;
    }
}
