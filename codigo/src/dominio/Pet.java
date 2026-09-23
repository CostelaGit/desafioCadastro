import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Pet {
    private String nome;
    private SEXO sexo;
    private TIPO tipo;
    private endereco endereco;
    private float idade;
    private float peso;
    private String raca;

    public Pet(String nome, SEXO sexo, TIPO tipo, endereco endereco, float idade, float peso, String raca){
     this.nome = nome;
     this.sexo = sexo;
     this.tipo = tipo;
     this.endereco = endereco;
     this.idade = idade;
     this.peso = peso;
     this.raca = raca;
    }

    public Pet(){

    }

    public void CadastrarPet(Scanner sc){
    try (BufferedReader br = new BufferedReader(new FileReader("formulario.txt"))) {
      String line;

      while ((line = br.readLine()) != null) {
        System.out.println(line);

        if (line.contains("1 -")) {
          try {
            nome = sc.nextLine().trim();
            if (nome.isEmpty() || nome.isBlank()) {
              nome = "NÃO INFORMADO";
            }
          } catch (Exception e) {
            System.out.println("precisa colocar nome");
          }
        }

        else if (line.contains("2 -")) {
          String tipoDigitado = sc.nextLine().trim();
          tipo = TIPO.valueOf(tipoDigitado.toUpperCase());
        }

        else if (line.contains("3 -")) {
          String sexoDigitado = sc.nextLine().trim();
          sexo = SEXO.valueOf(sexoDigitado.toUpperCase());
        }

        else if (line.contains("4 -")) {
          System.out.println("Número da casa: ");
          int numeroDaCasa = Integer.parseInt(sc.nextLine().trim());
          System.out.println("Cidade: ");
          String cidade = sc.nextLine();
          System.out.println("Rua: ");
          String rua = sc.nextLine();
          endereco = new endereco(numeroDaCasa, cidade, rua);
        }

        else if (line.contains("5 -")) {
          System.out.println("Idade: ");
          idade = sc.nextFloat();
          if (idade > 20) {
            throw new IllegalArgumentException("Idade inválida. A idade deve ser menor ou igual a 20.");
          }
        }

        else if (line.contains("6 -")) {
          System.out.println("Peso: ");
          float peso = sc.nextFloat();
          if (peso <= 0.5 || peso > 60) {
            throw new IllegalArgumentException("Peso inválido. O peso deve ser maior do que 0.5 e menor ou igual a 60.");
          }
        }

        else if (line.contains("7 -")) {
          System.out.println("Raça: ");
          raca = sc.nextLine().trim();
          if (raca.isEmpty() || raca.isBlank()) {
            raca = "NÃO INFORMADO";
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading file.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

}
}
