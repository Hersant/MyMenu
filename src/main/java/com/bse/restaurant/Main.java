package com.bse.restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int number = 0;
        Order order = new Order();

        do {
            order.displayMenus();
            Scanner scanner = new Scanner(System.in);
            try {
                number = scanner.nextInt();
                if (number < 1 || number > 3) {
                    System.out.println("Vous devez choisir un entier compris entre 1 et 3 !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vous devez choisir un entier compris entre 1 et 3 !");
            }
        } while (number < 1 || number > 3);

        System.out.println("Vous avez choisi le menu '" + order.displaySelectedMenu(number) + "'");
    }
}
