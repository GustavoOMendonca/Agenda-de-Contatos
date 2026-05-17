package utils;

import java.util.Scanner;

public class Entrada {

    private static final Scanner scanner = new Scanner(System.in);

    public static String lerTexto(String mensagem) {

        System.out.print(mensagem);

        return scanner.nextLine();
    }

    public static int lerInt(String mensagem) {

        System.out.print(mensagem);

        return Integer.parseInt(scanner.nextLine());
    }
}