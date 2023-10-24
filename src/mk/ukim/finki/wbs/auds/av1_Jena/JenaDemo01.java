package mk.ukim.finki.wbs.auds.av1_Jena;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;



public class JenaDemo01 {

    public static void main(String[] args) {

        String personURI = "https://plus.google.com/+JohnSmith";
        String fullName = "John Smith";

        Model model = ModelFactory.createDefaultModel();
        Resource johnSmith = model.createResource(personURI)
                        .addProperty(VCARD.FN, fullName);



    }
}
