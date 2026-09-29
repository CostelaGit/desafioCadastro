import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        final String NAO_INFORMADO = "Não informado";

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Cadastrar um novo pet\r\n" + //
                    "2. Alterar os dados do pet cadastrado\r\n" + //
                    "3. Deletar um pet cadastrado\r\n" + //
                    "4. Listar todos os pets cadastrados\r\n" + //
                    "5. Listar pets por algum critério (idade, nome, raça)\r\n" + //
                    "6. Sair\n");

            System.out.println("digite a opção que você deseja: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    try (BufferedReader br = new BufferedReader(new FileReader("formulario.txt"))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            if (linha.contains("1 -")) {
                                System.out.println(linha);
                                try { // Adicionando validação para nome e sobrenome
                                    String nomeEsobrenome = sc.nextLine();
                                    if (nomeEsobrenome.isEmpty()) {
                                        nomeEsobrenome = NAO_INFORMADO;
                                    }
                                    if (!nomeEsobrenome.matches("[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+")) {
                                        throw new IllegalArgumentException(
                                                "O nome e sobrenome devem conter apenas letras e um espaço entre eles.");
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("2 -")) {
                                System.out.println(linha);
                                try { // Adicionando validação para TIPO
                                    String tipo = sc.nextLine().toUpperCase();
                                    if (!(tipo.equals("CACHORRO") || tipo.equals("GATO"))) {
                                        throw new IllegalArgumentException(
                                                "Coloque uma das opções, CACHORRO ou GATO");
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("3 -")) {
                                System.out.println(linha);
                                try {
                                    String sexo = sc.nextLine().toUpperCase();
                                    if (!(sexo.equals("MACHO") || sexo.equals("FEMEA"))) {
                                        throw new IllegalArgumentException("Digite MACHO ou FEMEA");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("4 -")) {
                                System.out.println(linha);
                                System.out.println("Digite Número da casa: ");
                                int numCasa = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Digite o nome da cidade: ");
                                String cidade = sc.nextLine();
                                System.out.println("Digite o nome da rua: ");
                                String rua = sc.nextLine();
                            }
                            if (linha.contains("5 -")) {
                                System.out.println(linha);
                                Double idade = 0.0;
                                String idadeDigitada = sc.nextLine().trim();
                                try {
                                    if (idadeDigitada.isEmpty()) {
                                        idade = 0.0;
                                        break;
                                    } if(!idadeDigitada.matches("\\d+(\\.\\d+)?")) {
                                        throw new IllegalArgumentException("Idade deve ser um número válido.");
                                    } if (((idadeDigitada.contains(".")))) {
                                        idadeDigitada = idadeDigitada.replace(".", ",");
                                    }
                                    else {
                                        idade = Double.parseDouble(idadeDigitada);
                                    }
                                    if (idade > 20) {
                                        throw new IllegalArgumentException("Idade não pode ser maior do que 20.");
                                    }
                                    if ((idade > 0) || (idade < 1)){
                                        idade /= 12;
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("6 -")) {
                                System.out.println(linha);
                                String pesoDigitado = sc.nextLine().trim();
                                try {
                                    if (pesoDigitado.isEmpty()) {
                                        pesoDigitado = "0.0";
                                        break;
                                    }
                                    if (!pesoDigitado.matches("\\d+(\\.\\d+)?")) {
                                        throw new IllegalArgumentException("Peso deve ser um número válido.");
                                    }
                                    Double peso = Double.parseDouble(pesoDigitado);
                                    if (peso < 0.5 || peso > 60) {
                                        throw new IllegalArgumentException("Peso não pode ser menor que 0.5 ou maior que 60kg.");
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("7 -")) {
                                System.out.println(linha);
                                String raca = sc.nextLine().trim();
                                if (raca.isEmpty()) {
                                    raca = NAO_INFORMADO;
                                } if(!raca.matches("[A-Za-zÀ-ÿ ]+")) {
                                    throw new IllegalArgumentException("A raça deve conter apenas letras e espaços.");
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("\nPet cadastrado com sucesso!\n");
                    break;
                case 2:
                    System.out.println("opcao 2");
                    break;
                case 3:
                    System.out.println("opcao 3");
                    break;
                case 4:
                    System.out.println("opcao 4");
                    break;
                case 5:
                    System.out.println("opcao 5");
                    break;
                case 6:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        }
    }
}
