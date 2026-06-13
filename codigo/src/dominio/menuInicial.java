package dominio;

import java.io.IOException;
import java.util.Scanner;

public class menuInicial {

    perguntas pInicial = new perguntas();

    Scanner in = new Scanner(System.in);


    public String menuInicial = "1. Cadastrar um novo pet\n" +
            "2. Alterar os dados do pet cadastrado\n" +
            "3. Deletar um pet cadastrado\n" +
            "4. Listar todos os pets cadastrados\n" +
            "5. Listar pets por algum critério (idade, nome, raça)\n" +
            "6. Sair";


    public int rMenuInicial() {
        int result = 0;
        System.out.println(menuInicial);
        System.out.println("\n !! Favor digite a Opção pretendida !!");

        while (true) {
            try {
                result = Integer.parseInt(in.nextLine().trim());

                if (result >= 1 && result <= 6) {
                    break;
                } else {
                    System.out.println("\n !! Favor digite dentro das opções !!");
                    System.out.println(menuInicial + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n !! Apenas números são permitidos !!");
            }
        }

        return result;
    }

}
