import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExtractorNasa implements Extractor {
    public List<Conteudo> extract(String body) {
        List<Conteudo> conteudos = new ArrayList<>();
        JSONArray atributosJson = new JSONArray(body);

        for (Object atributos : atributosJson) {
            JSONObject nasa = new JSONObject(atributos.toString());

            var title = (String) nasa.get("title");
            var urlImage = (String) nasa.get("url");

            var conteudo = new Conteudo(title, urlImage);
            conteudos.add(conteudo);
        }

        return conteudos;
    } 
}
