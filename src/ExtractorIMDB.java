import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExtractorIMDB implements Extractor {
    public List<Conteudo> extract(String body) {
        List<Conteudo> conteudos = new ArrayList<>();
        JSONArray atributosJson = new JSONObject(body).getJSONArray("items");

        for (Object atributos : atributosJson) {
            JSONObject filme = new JSONObject(atributos.toString());

            var title = filme.get("title").toString();
            var image = filme.get("image").toString();
            String[] imageParts = image.split("(?<=@)");
            var urlImage = imageParts[0] + ".jpg";

            var conteudo = new Conteudo(title, urlImage);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
