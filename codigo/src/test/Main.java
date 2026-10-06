import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String NAO_INFORMADO = "Não informado";
        LocalDateTime time = LocalDateTime.now();

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
                    String nome = NAO_INFORMADO;
                    TIPO tipo = null;
                    SEXO sexo = null;
                    endereco enderecoPet = null;
                    float idade = 0;
                    float peso = 0;
                    String raca = NAO_INFORMADO;

                    try (BufferedReader br = new BufferedReader(new FileReader("formulario.txt"))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            if (linha.contains("1 -")) {
                                System.out.println(linha);
                                try { // Adicionando validação para nome e sobrenome
                                    nome = sc.nextLine().trim();
                                    if (nome.isEmpty()) {
                                        nome = NAO_INFORMADO;
                                    }
                                    if (!nome.matches("[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+")) {
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
                                    String tipoDigitado = sc.nextLine().trim().toUpperCase();
                                    if (!(tipoDigitado.equals("CACHORRO") || tipoDigitado.equals("GATO"))) {
                                        throw new IllegalArgumentException(
                                                "Coloque uma das opções, CACHORRO ou GATO");
                                    }
                                    tipo = TIPO.valueOf(tipoDigitado);
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("3 -")) {
                                System.out.println(linha);
                                try {
                                    String sexoDigitado = sc.nextLine().trim().toUpperCase();
                                    if (!(sexoDigitado.equals("MACHO") || sexoDigitado.equals("FEMEA"))) {
                                        throw new IllegalArgumentException("Digite MACHO ou FEMEA");
                                    }
                                    sexo = SEXO.valueOf(sexoDigitado);
                                } catch (IllegalArgumentException e) {
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
                                enderecoPet = new endereco(numCasa, cidade, rua);
                            }
                            if (linha.contains("5 -")) {
                                System.out.println(linha);
                                String idadeDigitada = sc.nextLine().trim();
                                try {
                                    if (idadeDigitada.isEmpty()) {
                                        idade = 0;
                                    } else if (!idadeDigitada.matches("\\d+(\\.\\d+)?")) {
                                        throw new IllegalArgumentException("Idade deve ser um número válido.");
                                    } else {
                                        idade = Float.parseFloat(idadeDigitada);
                                    }
                                    if (idade > 20) {
                                        throw new IllegalArgumentException("Idade não pode ser maior do que 20.");
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                            }
                            if (linha.contains("6 -")) {
                                System.out.println(linha);
                                String pesoDigitado = sc.nextLine().trim();
                                try {
                                    if (pesoDigitado.isEmpty()) {
                                        peso = 0;
                                    } else if (!pesoDigitado.matches("\\d+(\\.\\d+)?")) {
                                        throw new IllegalArgumentException("Peso deve ser um número válido.");
                                    } else {
                                        peso = Float.parseFloat(pesoDigitado);
                                        if (peso < 0.5 || peso > 60) {
                                            throw new IllegalArgumentException("Peso não pode ser menor que 0.5 ou maior que 60kg.");
                                        }
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            if (linha.contains("7 -")) {
                                System.out.println(linha);
                                raca = sc.nextLine().trim();
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

                    Pet pet = new Pet(nome, sexo, tipo, enderecoPet, idade, peso, raca);

                    
                    LocalDateTime tempoAtual = time;

                    try {
                        String tempoAtualFormatado = tempoAtual.format(DateTimeFormatter.ofPattern("yyyyddMM'T'HHmmss"));
                        String nomeArquivo = tempoAtualFormatado + "-" + nome.replace(" ", "").toUpperCase() + ".txt";

                        File pasta = new File("petsCadastrados");

                        File arquivoDestino = new File(pasta, nomeArquivo);

                        try (BufferedWriter br = new BufferedWriter(new FileWriter(arquivoDestino))){
                            br.write("1 - " + nome);
                            br.newLine();
                            br.write("2 - " + tipo);
                            br.newLine();
                            br.write("3 - " + sexo);
                            br.newLine();
                            br.write("4 - " + enderecoPet.getRua().toUpperCase() + ", " + enderecoPet.getNumeroDaCasa() + ", " + enderecoPet.getCidade().toUpperCase());
                            br.newLine();
                            br.write("5 - " + idade + " anos");
                            br.newLine();
                            br.write("6 - " + peso + " KGs");
                            br.newLine();
                            br.write("7 - " + raca);
                            br.newLine();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println("Nome do arquivo: " + nomeArquivo);
                    } catch (Exception e) {
                        System.out.println("Erro ao formatar a data: " + e.getMessage());
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
