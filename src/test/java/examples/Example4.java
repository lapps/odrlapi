package examples;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import odrlmodel.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;


/**
 * This class shows a very simple example creating a simple ODRL expression
 * @see <a href="http://www.w3.org/ns/odrl/2/#sec-example-4">sec-example-4</a>
 *
 * @author Victor
 * @author Keith Suderman
 */
public class Example4 {
 
    /**
     * @param args No arguments are needed
     */
    public static void main(String[] args) {

        //We create a policy. It can be anonymous, or it can receive a name (URI)
        Policy policy = new Policy("http://example.com/policy:4311");
        policy.setType(Policy.POLICY_REQUEST);
        //We create a permission to read and write
        Permission permission = new Permission();
        permission.setTarget("http://example.com/news:0099");
        permission.setActions(Arrays.asList(new Action("http://www.w3.org/ns/odrl/2/display")));
        permission.setAssignee(new Party("http://example.com/guest:0589"));
        policy.addRule(permission);

        JenaJSONLD.init();
        //We serialize the policy
//		 Resource r = ODRLRDF.getResourceFromPolicy(policy);
//		 Model model = r.getModel();
//		 model.write(System.out, "JSON-LD");
//        String rdf=ODRLRDF.getRDF(policy,Lang.TTL);
		 String json = ODRLRDF.getRDF(policy, Lang.JSONLD);
		 System.out.println(json);

		 System.out.println("\nN3");
		 System.out.println(ODRLRDF.getRDF(policy, Lang.N3));
		 System.out.println("\nNQUADS");
		 System.out.println(ODRLRDF.getRDF(policy, Lang.NQUADS));
		 System.out.println("\nNTRIPLES");
		 System.out.println(ODRLRDF.getRDF(policy, Lang.NTRIPLES));

		 Policy p = ODRLRDF.getPolicy(json, "JSON-LD");
		 String rdf = ODRLRDF.getRDF(p, Lang.TTL);
		 System.out.println(rdf);

//		 Model model = RDFDataMgr.loadModel("/tmp/example4.jsonld");
//		 model.write(System.out, "JSON-LD");


//		 Model model = ModelFactory.createDefaultModel();
//		 StringReader reader = new StringReader(json);
//		 model.read(reader, "JSON-LD");
//		 model.write(System.out, "TTL");
//        System.out.println(rdf);
        
    }
    
    
}


