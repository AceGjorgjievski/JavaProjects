package mk.ukim.finki.np.auds.av5.OldestPerson;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OldestPerson {

    public static void readData(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line=null,name=null;
        int max = -1;
        while((line = bufferedReader.readLine()) != null){
            String [] parts = line.split("\\s++");
            int local = Integer.parseInt(parts[1]);

            if(max < local){
                max = local;
                name = parts[0];
            }
        }

        System.out.printf("%s %d",name,max);
    }

    public static List<PersonDetails> readData2(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return bufferedReader.lines()
                .map(line -> new PersonDetails(line))
                .collect(Collectors.toList());
    }



    public static void main(String[] args) {
        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\finkiNP2.txt");

        try {
            List<PersonDetails> people = readData2(new FileInputStream(file));
            readData(new FileInputStream(file));
            if(people.stream().max(Comparator.naturalOrder()).isPresent()){
                System.out.println(people.stream().max(Comparator.naturalOrder()).get());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
