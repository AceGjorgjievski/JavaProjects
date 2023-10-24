package mk.ukim.finki.wbs.labs.lab_1_Jena.Task_2;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;


public class ReadingRDF {

    static final String inputFileName = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\wbs\\labs\\lab_1_Jena\\Task_2\\reading_files\\file.xml";

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        InputStream is = FileManager.get().open(inputFileName);

        if (is == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        //13
        model.read(is, "");
        model.write(System.out, "N-TRIPLES");

        String personalURI = "https://www.linkedin.com/in/ace-gjorgjievski-341821292/";
        Resource aceGjorgjievski = model.createResource(personalURI);

        //17
        String firstName = aceGjorgjievski.getProperty(VCARD.Given).getObject().toString();
        String lastName = aceGjorgjievski.getProperty(VCARD.Family).getObject().toString();
        String fullName = aceGjorgjievski.getProperty(VCARD.FN).getObject().toString();

        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("FullName: " + fullName);


    }
}
