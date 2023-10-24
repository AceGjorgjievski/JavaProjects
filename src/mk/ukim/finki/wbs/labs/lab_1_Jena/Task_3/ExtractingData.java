package mk.ukim.finki.wbs.labs.lab_1_Jena.Task_3;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtractingData {

    static final String inputFileName = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\wbs\\labs\\lab_1_Jena\\Task_3\\courses.finki.ukim.mk_pluginfile.php_238624_mod_resource_content_1_hifm-dataset.ttl.txt";


    public static void main(String[] args) throws FileNotFoundException {
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
//            System.out.println(object.asLiteral().toString());
            System.out.println("-------------------");
        }

        //24
        Property similarTo = model.getProperty("http://purl.org/net/hifm/ontology#similarTo");
        Property labelProperty = RDFS.label;

        Set<String> similarDrugNames = new HashSet<>();

        StmtIterator similarToStatements = model.listStatements(drug, similarTo, (RDFNode) null);

        while (similarToStatements.hasNext()) {
            Resource similarDrug = similarToStatements.nextStatement().getObject().asResource();

            StmtIterator similarLabelStatements = similarDrug.listProperties(labelProperty);
            if (similarLabelStatements.hasNext()) {
                String similarLabelValue = similarLabelStatements.nextStatement().getObject().asLiteral().getString();
                similarDrugNames.add(similarLabelValue);
            }
        }

        System.out.println("Drugs similar to Diazepam:");
        for (String name : similarDrugNames) {
            System.out.println(name);
        }



    }
}
