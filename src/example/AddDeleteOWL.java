package example;

import java.io.FileInputStream;
import java.io.IOException;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;  
import com.hp.hpl.jena.rdf.model.RDFNode;  
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;  


  
public class AddDeleteOWL {  
    public static void main(String[] args) {
        // create the model and import owl file
        OntModel ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
     try
     {
  	   ontModel.read(new FileInputStream("D:\\Fruits-lite.owl"),"");
     }
     catch(IOException ioe)
     {
            System.err.println(ioe.toString());
     }
     

      
        String testURI = "http://www.360iii.org/ontologies/fruit#OWLClass_56ef58aa_adf0_4329_9442_8f6fa51870cd"; 
     
        //增加Resource
        Resource test = ontModel.createResource(testURI);  
        test.addProperty(RDFS.label, ontModel.createLiteral("测试呢", "zh")); 
        test.addProperty(RDFS.label, ontModel.createLiteral("test", "en")); 

                    ontModel.createResource()  
                        .addProperty(RDFS.label, ontModel.createLiteral("Fuck，Why so hard!", "zh"))
                        .addProperty(RDFS.label, ontModel.createLiteral("you are crazy", "en"));   
  
        System.out.println("/r/n原始内容：");  
        ontModel.write(System.out);  
        
        // 删除 Statement           
        ontModel.remove(ontModel.listStatements(null, RDFS.label, (RDFNode)null));  
        ontModel.removeAll(null, RDFS.label, (RDFNode)null);  
          
        System.out.println("\n删除后的内容：");  
        ontModel.write(System.out);  

        ontModel.add(test, VCARD.N, ontModel.createResource()  
        		.addProperty(RDFS.label, ontModel.createLiteral("Fuck，Why so hard!", "zh"))
        		.addProperty(RDFS.label, ontModel.createLiteral("you are crazy", "en")));   
        System.out.println("\n重新增加后的内容：");  
        ontModel.write(System.out);  
    }  

}  

