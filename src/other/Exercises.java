package other;

import java.io.*;
import java.util.Arrays;
import java.util.List;


class Other {
    private String s;
    public Other(String s){
        this.s = s;
    }

    public String read(File f) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(f));

        StringBuilder sb = new StringBuilder();
        String line = null;

        while((line = bf.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.s;
    }
}

public class Exercises {

    public static void execute(InputStream in, PrintStream out) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));

        int broj = (int)bf.lines()
                .mapToInt(Integer::parseInt)
                .filter(i -> i%2 == 0)
                .count();

        out.println(broj);
        //out.flush();

    }


    public static void main(String[] args) throws IOException {
//        execute(System.in,System.out);

//        Other o = new Other();
//        String path = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\os\\aud_2\\other.txt";
//        System.out.println(o.read(new File(path)));

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String pom = bf.readLine();
//        String [] data = pom.split("");
//
//        for(int i=0; i< data.length; i++) {
//            Other other = new Other(data[i]);
//            System.out.println(other);
//        }




        String num = "0000:000:00:00:0007";
        String [] parts = num.split(":");
        Character.isLetter(2);
        parts[1].substring(0,1);
        System.out.println();




    }
}
