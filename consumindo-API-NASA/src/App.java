import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

public class App{

    public static void main(String[] args) throws Exception{

        //https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json


        
        // fazer uma conexao HTTP e buscar os top filmes
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        
        List<conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            conteudo conteudo = conteudos.get(i);

            //String urlImagem = conteudo.get("url")
            //   .replaceAll("(@+)(.*).jpg", "$1.jpg");
            //String titulo = conteudo.get("title");
            
                
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            geradora.cria(inputStream, nomeArquivo);
        
            System.out.println(conteudo.getTitulo());
            System.out.println();
            
        }
    }
}
