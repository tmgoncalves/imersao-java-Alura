import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

public class App{

public static void main(String[] args) throws Exception{

    //https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json


    
    // fazer uma conex~ao HTTP e buscar os top filmes
    // documentacao -> httprequest javadoc
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    URI endereco = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();
    // extrair somente os dados que interessam (titulo, poster, ckassificacao)
    // essa etapa e conhecida como parser.
    // uma das maneiras de limpar os dados e a utilizacao e o Regex
    // site regex: regex101.com
    var parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(body);
    //System.out.println(listaDeFilmes.size());
    //System.out.println(listaDeFilmes.get(0));

    // exibir e manipular os dados

    for (Map<String,String> filme : listaDeFilmes) {

        String urlImagem = filme.get("image");
        String titulo = filme.get("title");
        String nomeArquivo = titulo + ".png";

        InputStream inputStream = new URL(urlImagem).openStream();

        var geradora = new GeradoraDeFigurinhas();
        geradora.cria(inputStream, nomeArquivo);
    
        System.out.println(filme.get("title"));
        // System.out.println(filme.get("image"));
        // System.out.println(filme.get("imDbRating"));

        System.out.println();
        
    }

    }
}
