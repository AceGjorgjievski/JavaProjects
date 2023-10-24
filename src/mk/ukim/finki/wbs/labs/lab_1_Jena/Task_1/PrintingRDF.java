package mk.ukim.finki.wbs.labs.lab_1_Jena.Task_1;


import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;

public class PrintingRDF {

    public static void main(String[] args) {
        //3
        Model model = ModelFactory.createDefaultModel();

        String personalURI = "https://www.linkedin.com/in/ace-gjorgjievski-341821292/";
        String firstName = "Ace";
        String lastName = "Gjorgjievski";
        String fullName = firstName + " " + lastName;



        String country = "Macedonia";
        String city = "Skopje";
        String continent = "Europe";
        String gender = "male";
        String bDay = "07-05-2001";
        String email = "ace.gjorgjievski6@gmail.com";
        String age = "22";

        //4 & 5
        Resource aceGjorgjievski = model.createResource(personalURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(FOAF.gender, gender)
                .addProperty(VCARD.ADR, model.createResource()
                        .addProperty(VCARD.Country, country)
                        .addProperty(model.createProperty("https://example.org/continent"), continent)
                        .addProperty(model.createProperty("https://example.org/city"), city))
                .addProperty(VCARD.BDAY, bDay)
                .addProperty(VCARD.EMAIL, email)
                .addProperty(FOAF.age, age)
                .addProperty(VCARD.Given, firstName)
                .addProperty(VCARD.Family, lastName);


        //6
        StmtIterator iterator = model.listStatements();

        System.out.println(" == \t Printing with model.listStatements():  \t == ");
        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();

            String objectStr = null;
            if (object instanceof Resource) {
                objectStr = object.toString();
            } else {
                objectStr = "\"" + object.toString() + "\"";
            }

            System.out.print(subject.toString() + " - " + predicate.toString() + " - " + objectStr);
            System.out.println(" .");
        }

        //8
        System.out.println("================================================");
        System.out.println("\t ===== Printing with model.print() in RDF/XML ===== \t");
        model.write(System.out);
        System.out.println("================================================");
        System.out.println("\t ===== Printing with model.print() in pretty RDF/XML ===== \t");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println("================================================");
        System.out.println("\t ===== Printing with model.print() in N - Triples ===== \t");
        model.write(System.out, "N-TRIPLES");
        System.out.println("================================================");
        System.out.println("\t ===== Printing with model.print() in Turtle ===== \t");
        model.write(System.out, "TURTLE");

    }
}
