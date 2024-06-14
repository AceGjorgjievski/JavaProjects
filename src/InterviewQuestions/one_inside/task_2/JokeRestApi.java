package InterviewQuestions.one_inside.task_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class JokeRestApi {

    private static String URL = "https://official-joke-api.appspot.com/random_joke";

    public static void main(String[] args) {
        List<Joke> jokes = fetchJokesFromApi();
    }

    private static List<Joke> fetchJokesFromApi() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final String body = response.body();
            ObjectMapper mapper = new ObjectMapper();
            List<Joke> jokeList = new ArrayList<>();

            final JsonNode jsonNode = mapper.readTree(body);


            /*
            for(Map<String, JsonNode> map : jsonNode._children) {
                String type = node.get("type").asText();
                String setup = node.get("setup").asText();
                String punchline = node.get("punchline").asText();
                String id = node.get("id").asText();

                System.out.println(type);
                System.out.println(setup);
                System.out.println(punchline);
                System.out.println(id);
                break;
            }
            *
             */


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateHtml(List<Joke> jokes) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div>\n");
        for (Joke joke : jokes) {
            htmlBuilder.append(String.format("<div value=\"%s\">%s - %s - %s</div>\n",
                    joke.getId(), joke.getPunchline(), joke.getSetup(), joke.getType()));
        }
        htmlBuilder.append("</div>");
        return htmlBuilder.toString();
    }
}
