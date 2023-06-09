import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {

    public List<conteudo>extraiConteudos(String json) {

        // extrair somente os dados que interessam (titulo, poster, ckassificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos;
        for (Map<String, String> atributos : listaDeAtributos) {

            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image");
            var Conteudo = new conteudo(titulo, urlImagem);

            conteudos.add(Conteudo);
        }
        return conteudos;
    }
}
