package mk.ukim.finki.aps.labs.lab6.StatichkoRutijaranje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



class IpNetwork{
    private String wholeIpAdress;

    public IpNetwork(String wholeIpAdress) {
        this.wholeIpAdress = wholeIpAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpNetwork that = (IpNetwork) o;
        return Objects.equals(wholeIpAdress, that.wholeIpAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wholeIpAdress);
    }

    @Override
    public String toString() {
        return ""+wholeIpAdress;
    }
}


public class RoutingHashJava {

    /*
Влез:
5
10.10.1.3
34.55.34.0,123.3.3.0,67.5.5.0,122.45.4.0
14.4.12.4
167.12.145.10,45.55.5.0,23.2.3.0
12.14.3.3
146.6.7.0,55.55.43.0,23.66.5.4.0
55.5.5.5
12.1.1.0
34.5.5.1
14.4.4.0
2
10.10.1.3
34.44.34.12
55.5.5.5
12.1.1.233
Излез:
ne postoi
postoi
     */

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
            String inputInterface = bf.readLine();
            String inputNetwork = bf.readLine();

            if(map.containsKey(inputInterface)) { // ako vo mapata go ima kluchot
                List<String> local = map.get(inputInterface);//togash zemi ja cela lista

                if(areMatching(local,inputNetwork)) {//ako vo listata ima mrezha kako inputNetwork
                    System.out.println("postoi");
                } else {
                    System.out.println("ne postoi");
                }
            } else {
                System.out.println("ne postoi");
            }
        }
    }

    private static boolean areMatching(List<String> local, String inputNetwork) {
        if(local == null) return false;
        int flagCounter = 0;

        for(int i=0; i<local.size(); i++) {
            String line = local.get(i);
            String [] parts = line.split("\\.");//podeli go i-ot element vo listata so . i smesti go vo string []
            String [] parts2 = inputNetwork.split("\\.");//isto i za inputNetwork

            //34.55.34.0
            //34.44.34.12
            int j;
            for(j=0; j<parts.length-1; j++) {//odime do parts.length-1, bidejkji gi gledame samo prvite 3 okteta bidejkji imaat /24 subMask
                if(parts[j].equals(parts2[j])) { //ako 1ot element od ednoto i 1 element od drugoto se isti
                    flagCounter++;//zgolemi go brojachot
                } else break;//ako ne odi na naredno
            }
            if(flagCounter == 3) {//ovde e 3 bidejkji flag counter-ot 3 pati se zgolemil i toa znachi deka prvite 3 okteti se isti
                //togash proveri gi poslednite 3 okteti dali se 'within its bounds'
                if(Integer.parseInt(parts2[parts2.length-1]) > 0 &&
                        Integer.parseInt(parts2[parts2.length-1]) < 255) {
                    return true;
                } else return false;
            }
            flagCounter = 0;
        }

        return false;
    }


}

/*  OVA OVDE E DEKA PRVO RESHAVAV SO PO EDNA INPUT ADRESA, A KOGA VIDOV NA PASTEBIN PRIMER DEKA
POVEKJE SE NAVEDUVALE VO TEST PRIMERITE ODDELENI SO ZAPIRKA
for(int i=0; i<N; i++) {
            String inputIpInterface = bf.readLine();
            String inputIpNetwork = bf.readLine();

            IpInterface ipInterface = new IpInterface(inputIpInterface);


            map.put(ipInterface, new ArrayList<>());
        }

        int M = Integer.parseInt(bf.readLine());

        for(int i=0; i<M; i++) {
            String inputIpInterface = bf.readLine();
            String inputIpNetwork = bf.readLine();

            IpInterface ipInterface = new IpInterface(inputIpInterface);

            if(map.containsKey(ipInterface)) {
                String [] parts = inputIpNetwork.split("\\.");//split by dots
                int lastOctet = Integer.parseInt(parts[3]);

                if(lastOctet > 0 && lastOctet < 255) System.out.println("postoi");
                else System.out.println("nepostoi");
            } else System.out.println("nepostoi");

        }
 */