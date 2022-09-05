package mk.ukim.finki.vezhbi.algoritmi.vezhb2.hash.statichkoRutiranje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IpNetworks {
    String network;

    public IpNetworks(String network) {
        this.network = network;
    }

    @Override
    public String toString() {
        return ""+network;
    }
}
public class RoutingHashJava {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        Map<String, List<String>> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            String inputInterface = bf.readLine();
            String [] parts = bf.readLine().split(",");

            List<String> stringList = new ArrayList<>();
            for(String s : parts) {
                stringList.add(s);
            }

            map.put(inputInterface,stringList);
        }

//        System.out.println(map);
        int M = Integer.parseInt(bf.readLine());

        for(int i=0; i<M; i++) {
            String ipInterface = bf.readLine();
            String ipNetwork = bf.readLine();

            IpNetworks ipNetworks = new IpNetworks(ipNetwork);

            if(map.containsKey(ipInterface)) {
                List<String> local = map.get(ipInterface);

                if(checkMatching(local,ipNetwork)) {
                    System.out.println("postoi");
                } else {
                    System.out.println("ne postoi");
                }
            } else {
                System.out.println("ne postoi");
            }
        }
    }

    private static boolean checkMatching(List<String> list, String ipNetwork) {
        if(list == null) return false;
        int finalCounter = 0;

        for(int i=0; i<list.size(); i++) {
            String line = list.get(i);
            String [] parts = line.split("\\.");
            String [] parts2 = ipNetwork.split("\\.");

            for(int j=0; j<parts.length-1; j++) {
                if(parts[j].equals(parts2[j])) {
                    finalCounter++;
                } else break;
            }
            if(finalCounter == 3) {
                if(Integer.parseInt(parts2[3]) > 0 &&
                Integer.parseInt(parts2[3]) < 255) {
                    return true;
                } else return false;
            }
            finalCounter = 0;

        }

        return false;
    }
}
