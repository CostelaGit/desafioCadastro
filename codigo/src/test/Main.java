package test;

import dominio.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Olá seja bem vindo ao desafioCadastro!!!\n");
        menuInicial mn = new menuInicial();

        while (true) {

            int opção = mn.rMenuInicial();
            switch (opção){
                case 1 -> {
                    PET nvpet = new PET();
                    nvpet.PET();
                    nvpet.serializar();
                }
                case 4 -> {

                }
                case 6 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("\nOpção ainda não implementada.\n");
            }
        }
    }
}
