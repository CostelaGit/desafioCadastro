import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class LeitorArquivo{
    public static void main(String []args){
        Path caminho = Path.of("formulario.txt");
    
        try {
        String conteudo = Files.readString(caminho);
        System.out.println(conteudo);

    } catch(IOException e){
        e.printStackTrace();
    }

    }
}