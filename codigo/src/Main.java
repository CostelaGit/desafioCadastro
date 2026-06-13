import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("file.txt");
        try {
            boolean isExisted = file.exists();

            if (isExisted) {
                file.delete();
                System.out.println("arquivo existente, deletado com sucesso!");
            } else {
                file.createNewFile();

                try (FileWriter fw = new FileWriter(file)) {
                    fw.write("1 - Qual o nome e sobrenome do pet?\n" +
                            "2 - Qual o tipo do pet (Cachorro/Gato)?\n" +
                            "3 - Qual o sexo do animal?\n" +
                            "4 - Qual endereço e bairro que ele foi encontrado?\n" +
                            "5 - Qual a idade aproximada do pet?\n" +
                            "6 - Qual o peso aproximado do pet?\n" +
                            "7 - Qual a raça do pet?\n");
                }

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String linha;
                    while ((linha = br.readLine()) != null) {
                        System.out.println(linha);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}