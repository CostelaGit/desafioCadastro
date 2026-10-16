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
                    Passo5();
                    break;
                case 3:
                    System.out.println("opcao 3");
                    break;
                case 4:
                    System.out.println("opcao 4");
                    break;
                case 5:
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

    public static void Passo5() {
        Scanner sc = new Scanner(System.in);
        File pasta = new File("petsCadastrados");

        if (!pasta.exists() || !pasta.isDirectory()) {
            System.out.println("Nenhum pet cadastrado foi encontrado.");
            return;
        }

        File[] arquivos = pasta.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum pet cadastrado foi encontrado.");
            return;
        }

        System.out.println("\n=== Busca de pets cadastrados ===");
        System.out.println("1 - CACHORRO");
        System.out.println("2 - GATO");
        System.out.println("3 - TODOS");
        System.out.print("Escolha o tipo do animal: ");

        String tipoBusca = null;
        String escolhaTipo = sc.nextLine().trim();

        switch (escolhaTipo) {
            case "1":
                tipoBusca = "CACHORRO";
                break;
            case "2":
                tipoBusca = "GATO";
                break;
            case "3":
                tipoBusca = "TODOS";
                break;
            default:
                System.out.println("Tipo inválido. Use 1, 2 ou 3.");
                return;
        }

        String criterioExtra = null;
        String valorExtra = null;

        System.out.print("Deseja filtrar por mais um critério? (S/N): ");
        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            System.out.println("\nCritérios disponíveis:");
            System.out.println("1 - Nome");
            System.out.println("2 - Sexo");
            System.out.println("3 - Idade");
            System.out.println("4 - Peso");
            System.out.println("5 - Raça");
            System.out.println("6 - Endereço");
            System.out.print("Escolha o critério: ");

            String criterioDigitado = sc.nextLine().trim();
            switch (criterioDigitado) {
                case "1":
                    criterioExtra = "nome";
                    break;
                case "2":
                    criterioExtra = "sexo";
                    break;
                case "3":
                    criterioExtra = "idade";
                    break;
                case "4":
                    criterioExtra = "peso";
                    break;
                case "5":
                    criterioExtra = "raca";
                    break;
                case "6":
                    criterioExtra = "endereco";
                    break;
                default:
                    System.out.println("Critério inválido.");
                    return;
            }

            System.out.print("Digite o valor para o critério: ");
            valorExtra = sc.nextLine().trim();
        }

        int contador = 0;
        for (File arquivo : arquivos) {
            if (!arquivo.isFile()) {
                continue;
            }

            try {
                java.util.Map<String, String> dados = lerDadosPet(arquivo);

                String tipoPet = dados.getOrDefault("tipo", "");
                if (!tipoBusca.equals("TODOS") && !normalizar(tipoPet).equals(normalizar(tipoBusca))) {
                    continue;
                }

                boolean atendeCriteroExtra = true;
                if (criterioExtra != null && !valorExtra.isEmpty()) {
                    atendeCriteroExtra = atenderCriterio(dados, criterioExtra, valorExtra);
                }

                if (!atendeCriteroExtra) {
                    continue;
                }

                contador++;
                System.out.println(contador + ". " + formatarResumoPet(dados));
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
            }
        }

        if (contador == 0) {
            System.out.println("Nenhum pet encontrado com os critérios informados.");
        }
    }

    private static java.util.Map<String, String> lerDadosPet(File arquivo) throws IOException {
        java.util.Map<String, String> dados = new java.util.HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                int posicaoSeparador = linha.indexOf(" - ");
                if (posicaoSeparador <= 0) {
                    continue;
                }

                String chave = linha.substring(0, posicaoSeparador).trim();
                String valor = linha.substring(posicaoSeparador + 3).trim();

                switch (chave) {
                    case "1":
                        dados.put("nome", valor);
                        break;
                    case "2":
                        dados.put("tipo", valor);
                        break;
                    case "3":
                        dados.put("sexo", valor);
                        break;
                    case "4":
                        dados.put("endereco", valor);
                        break;
                    case "5":
                        dados.put("idade", valor);
                        break;
                    case "6":
                        dados.put("peso", valor);
                        break;
                    case "7":
                        dados.put("raca", valor);
                        break;
                    default:
                        break;
                }
            }
        }

        return dados;
    }

    private static String formatarResumoPet(java.util.Map<String, String> dados) {
        String nome = dados.getOrDefault("nome", "Não informado");
        String tipo = dados.getOrDefault("tipo", "Não informado");
        String sexo = dados.getOrDefault("sexo", "Não informado");
        String endereco = dados.getOrDefault("endereco", "Não informado");
        String idade = dados.getOrDefault("idade", "Não informado");
        String peso = dados.getOrDefault("peso", "Não informado");
        String raca = dados.getOrDefault("raca", "Não informado");

        return nome + " - " + tipo + " - " + sexo + " - " + endereco + " - " + idade + " - " + peso + " - " + raca;
    }

    private static boolean atenderCriterio(java.util.Map<String, String> dados, String criterio, String valor) {
        String valorPesquisa = normalizar(valor);

        switch (criterio) {
            case "nome":
                return normalizar(dados.getOrDefault("nome", "")).contains(valorPesquisa);
            case "sexo":
                return normalizar(dados.getOrDefault("sexo", "")).contains(valorPesquisa);
            case "idade":
                return compararNumero(dados.getOrDefault("idade", "0"), valorPesquisa);
            case "peso":
                return compararNumero(dados.getOrDefault("peso", "0"), valorPesquisa);
            case "raca":
                return normalizar(dados.getOrDefault("raca", "")).contains(valorPesquisa);
            case "endereco":
                return normalizar(dados.getOrDefault("endereco", "")).contains(valorPesquisa);
            default:
                return false;
        }
    }

    private static boolean compararNumero(String valorArquivo, String valorPesquisa) {
        try {
            String numeroArquivo = valorArquivo.replaceAll("[^0-9,\\.]", "").replace(',', '.');
            String numeroPesquisa = valorPesquisa.replaceAll("[^0-9,\\.]", "").replace(',', '.');

            if (numeroArquivo.isEmpty() || numeroPesquisa.isEmpty()) {
                return false;
            }

            float valorArquivoFloat = Float.parseFloat(numeroArquivo);
            float valorPesquisaFloat = Float.parseFloat(numeroPesquisa);
            return valorArquivoFloat == valorPesquisaFloat;
        } catch (NumberFormatException e) {
            return normalizar(valorArquivo).contains(valorPesquisa);
        }
    }

    private static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }

        String semAcentos = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD);
        semAcentos = semAcentos.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return semAcentos.toLowerCase(java.util.Locale.ROOT);
    }
}
