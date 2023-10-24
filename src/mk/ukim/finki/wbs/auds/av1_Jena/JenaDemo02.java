package mk.ukim.finki.wbs.auds.av1_Jena;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class JenaDemo02 {

    public static void main(String[] args) {
        String personURI = "https://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;


        Model model = ModelFactory.createDefaultModel();

        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N, model.createResource())
                .addProperty(VCARD.Given, givenName)
                .addProperty(VCARD.Family, familyName);

        StmtIterator iter = model.listStatements();

        while(iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            System.out.println(subject.toString());
            System.out.println(" " + predicate.toString() + " ");
            if(object instanceof Resource) {
                System.out.println(object.toString());
            } else {
                System.out.println("\"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }

}
