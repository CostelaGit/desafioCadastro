package dominio;

import java.io.*;

public class perguntas {
    File file = new File("formulario.txt");

    public perguntas(){
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("1 - Qual o nome e sobrenome do pet?\n" +
                    "2 - Qual o tipo do pet (Cachorro/Gato)?\n" +
                    "3 - Qual o sexo do animal? (Macho/Femea)\n" +
                    "4 - Qual endereço e bairro que ele foi encontrado?\n" +
                    "5 - Qual a idade aproximada do pet?\n" +
                    "6 - Qual o peso aproximado do pet?\n" +
                    "7 - Qual a raça do pet?\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarPerguntas() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
