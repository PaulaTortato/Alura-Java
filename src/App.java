import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Extractor jsonExtractor = new ExtractorIMDB();
        // String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        // Extractor jsonExtractor = new ExtractorNasa();
        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";

        Extractor jsonExtractor = new ExtractorLinguagens();
        String url = "https://linguagens-api-pcwt.herokuapp.com/linguagens";
        
        ClientHTTP client = new ClientHTTP();
        String body = client.serverUp(url);

        List<Conteudo> conteudos = jsonExtractor.extract(body);

        StickerGenerator generator = new StickerGenerator();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);
            
            try {
                InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
                generator.create(inputStream, conteudo.getTitle());
            } catch (Exception e) {
                continue;
            }
    
            System.out.println(conteudo.getTitle());
        }
    }
}
