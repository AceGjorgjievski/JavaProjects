package mk.ukim.finki.wbs.auds.av1_Jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class JenaDemo04 {

    static final String inputFileName = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\wbs\\auds\\av1_Jena\\RDF-1.rdf";

    public static void main(String[] args) {

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);

        if(in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        model.read(in, "");

        model.write(System.out, "TTL");
    }
}
