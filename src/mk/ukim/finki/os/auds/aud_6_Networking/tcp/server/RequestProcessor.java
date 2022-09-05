package mk.ukim.finki.os.auds.aud_6_Networking.tcp.server;

import java.util.HashMap;

public class RequestProcessor {

    private String command;
    private String uri;
    private String status;
    private HashMap<String,String> map;

    private RequestProcessor(String [] data) {
        String cmd = data[0];
        String [] parts = cmd.split("\\s++");
        this.command = parts[0];
        this.uri =  parts[1];
        this.status = parts[2];
        this.map = new HashMap<>();

        for(int i=1; i<data.length; i++) {
            parts = data[i].split(":\\s++");
            map.put(parts[0],parts[1]);
        }
    }

    public static RequestProcessor of(String input){
        return new RequestProcessor(input.split("\n"));
    }

    public String getCommand() {
        return command;
    }

    public String getUri() {
        return uri;
    }

    public String getStatus() {
        return status;
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
