package mk.ukim.finki.wbs.auds.av1_Jena;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

public class JenaDemo03 {

    public static void main(String[] args) {
        String personURI = "https://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;


        Model model = ModelFactory.createDefaultModel();

        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N,
                        model.createResource()
                            .addProperty(VCARD.Given, givenName)
                            .addProperty(VCARD.Family, familyName));


        System.out.println("==========================");
        System.out.println("Print as RDF/XML:");
        model.write(System.out);
        System.out.println("==========================");

        System.out.println("==========================");
        System.out.println("Print as pretty RDF/XML:");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println("==========================");

        System.out.println("==========================");
        System.out.println("Print as N-Triples:");
        model.write(System.out, "N-TRIPLES");
        System.out.println("==========================");

        System.out.println("==========================");
        System.out.println("Print as Turtle:");
        model.write(System.out, "TURTLE");
        System.out.println("==========================");

    }
}
