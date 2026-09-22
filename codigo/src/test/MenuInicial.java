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

            if ((op == 0) || (op < 0) || (op > 6)) {
                // throw new IllegalArgumentException("Não é uma opção válida, por favor digite apenas as opções do menu");
                System.out.println("\nNão é uma opção válida, por favor digite apenas as opções do menu\n");
            }

        }
    }
}
