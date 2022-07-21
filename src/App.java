import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.JSONArray;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI uri = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        JSONArray filmes = new JSONObject(body).getJSONArray("items");

        StickerGenerator generator = new StickerGenerator();

        for (Object f : filmes) {
            JSONObject filme = new JSONObject(f.toString());

            var title = filme.get("title").toString();
            var image = filme.get("image").toString();
            String[] imageParts = image.split("(?<=@)");
            var imageURL = imageParts[0] + ".jpg";

            try {
                InputStream inputStream = new URL(imageURL).openStream();
                generator.create(inputStream, title);
            } catch (Exception e) {
                continue;
            }

            System.out.println(title);
        }
    }
}
