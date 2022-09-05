package mk.ukim.finki.vezhbi.os.tcp;

import java.util.HashMap;

public class RP {

    private String command;
    private String uri;
    private String version;
    private HashMap<String, String> headers;

    private RP(String [] data) {
        String cmd = data[0];
        String [] parts = cmd.split("\\s++");
        this.command = parts[0];
        this.uri = parts[1];
        this.version = parts[2];

        headers = new HashMap<>();

        for(int i=1; i<data.length; i++) {
            parts = data[i].split("\\s++");
            headers.put(parts[0],parts[1]);
        }
    }

    public static RP of(String messageFromClient) {
        return new RP(messageFromClient.split("\n"));
    }

    public String getCommand() {
        return command;
    }

    public String getUri() {
        return uri;
    }
}
