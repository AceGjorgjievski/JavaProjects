package mk.ukim.finki.wbs.labs.lab_1_Jena.Task_3;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtractingData {

    static final String inputFileName = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\wbs\\labs\\lab_1_Jena\\Task_3\\courses.finki.ukim.mk_pluginfile.php_238624_mod_resource_content_1_hifm-dataset.ttl.txt";

    public static void main(String[] args) throws IOException {
        Model model = ModelFactory.createDefaultModel();
        try (InputStream str = new FileInputStream(inputFileName)) {
            model.read(str, null, "TTL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //20
        ResIterator iterator = model.listResourcesWithProperty(RDFS.label);
        List<String> meds = new ArrayList<>();
        while (iterator.hasNext())
            meds.add(iterator.nextResource().getProperty(RDFS.label).getLiteral().getString());

        AtomicInteger counter = new AtomicInteger(1);
        meds.stream()
                .distinct()
                .sorted(Comparator.naturalOrder())
                .map(item -> {
                    int index = counter.getAndIncrement();
                    return index + ". " + item;
                })
                .forEach(System.out::println);

        //23
        String drugURI = "http://purl.org/net/hifm/data#965405"; // Diazepam
        Resource drug = model.createResource(drugURI);

        StmtIterator stmtIterator = model.listStatements(drug, (Property) null, (RDFNode) null);
        while (stmtIterator.hasNext()) {
            Statement statement = stmtIterator.nextStatement();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();
            System.out.println("Relation: " + predicate.getURI());
            System.out.println("Value: " + object.toString());
            System.out.println("-------------------");
        }

        //24 & 25
        Property similarTo = model.getProperty("http://purl.org/net/hifm/ontology#similarTo");
        Property priceProperty = model.getProperty("http://purl.org/net/hifm/ontology#refPriceWithVAT");

        StmtIterator priceStatements = drug.listProperties(priceProperty);
        if (priceStatements.hasNext()) {
            Statement priceStatement = priceStatements.nextStatement();
            RDFNode priceObject = priceStatement.getObject();
            System.out.println("Price of the selected drug (Diazepam): " + priceObject.toString());
        } else {
            System.out.println("Price not found for the selected drug (Diazepam).");
        }

        Set<String> similarDrugInfo = new HashSet<>();
        StmtIterator similarToStatements = model.listStatements(drug, similarTo, (RDFNode) null);

        while (similarToStatements.hasNext()) {
            Resource similarDrug = similarToStatements.nextStatement().getObject().asResource();

            StmtIterator similarLabelStatements = similarDrug.listProperties(RDFS.label);
            StmtIterator similarPriceStatements = similarDrug.listProperties(priceProperty);

            if (similarLabelStatements.hasNext()) {
                String similarLabelValue = similarLabelStatements.nextStatement().getObject().asLiteral().getString();
                String similarPriceValue = similarPriceStatements.hasNext()
                        ? similarPriceStatements.nextStatement().getObject().toString()
                        : "Price not found";

                similarDrugInfo.add("Name: " + similarLabelValue + ", Price: " + similarPriceValue);
            }
        }

        System.out.println("Drugs similar to Diazepam and their prices:");
        for (String info : similarDrugInfo) {
            System.out.println(info);
        }
    }
}
