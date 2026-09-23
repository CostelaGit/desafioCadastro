import java.util.Scanner;

public class MenuInicial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Cadastrar um novo pet\r\n" + //
                                "2. Alterar os dados do pet cadastrado\r\n" + //
                                "3. Deletar um pet cadastrado\r\n" + //
                                "4. Listar todos os pets cadastrados\r\n" + //
                                "5. Listar pets por algum critério (idade, nome, raça)\r\n" + //
                                "6. Sair\n");
            System.out.println("digite a opção que você deseja: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    LeitorArquivo.LerTudo(args);
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
                    System.exit(0);
                default:
                    break;
            }

        }
    }
}
