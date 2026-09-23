import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    Pet pet = new Pet();
                    pet.CadastrarPet(sc);
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
                    break;
            }

        }
    }
}
