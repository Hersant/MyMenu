package com.bse.restaurant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class Order {
    private int nbMenu;
    private int nbSide;
    private int nbDrink;

    public static final String CSV_FILE_NAME = "src/main/resources/csv/order.csv";

    public Order() {
        this.nbMenu = -1;
        this.nbSide = -1;
        this.nbDrink = -1;
    }

    /**
     * Display the available menus in the restaurant.
     */
    public void displayMenus() {
        System.out.println("\nMENU : ");
        System.out.println("1. Poulet");
        System.out.println("2. Boeuf");
        System.out.println("3. Végétarien");
        System.out.println("0. Pour annuler la commande");
        System.out.println("Que désirez-vous (entrez le chiffre correspondant) ?");
    }

    /**
     * Get the selected menu.
     * @param menuNumber The number of the selected menu
     * @return The name of the selected menu
     */
    public String getSelectedMenu(int menuNumber) {
        if (menuNumber == 1) {
            return "Poulet";
        } else if (menuNumber == 2) {
            return "Boeuf";
        } else { // The menu number is definitely 3
            return "Végétarien";
        }
    }

    /**
     * Run asking process for a menu.
     */
    public void runMenu() {
        int number = -1;

        do {
            displayMenus();
            Scanner scanner = new Scanner(System.in);
            try {
                number = scanner.nextInt();
                if (number == 0) {
                    System.out.println("Merci pour votre visite.");
                    System.exit(0);
                } else if (number < 1 || number > 3) {
                    System.out.println("Vous devez choisir un entier compris entre 0 et 3 !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vous devez choisir un entier compris entre 0 et 3 !");
            }
        } while (number < 0 || number > 3);

        System.out.println("Vous avez choisi comme menu : " + getSelectedMenu(number));

        this.nbMenu = number;
    }

    /**
     * Display sides depending on the selected menu:
     * All sides are displayed if the selected menu is Beef or Chicken.
     * @param nbMenu The menu number
     */
    public void displaySides(int nbMenu) {
        System.out.println("\nACCOMPAGNEMENT : ");

        System.out.println("0. Aucun");
        System.out.println("1. Riz");

        if (nbMenu == 1 || nbMenu == 2) { // Display more sides if nbMenu is 1 (chicken) or 2 (Beef)
            System.out.println("2. Frites");
            System.out.println("3. Légumes frais");
        }

        System.out.println("Que désirez-vous (entrez le chiffre correspondant) ?");
    }

    /**
     * Get the selected side.
     * @param nbSide The number corresponding to the selected side.
     * @return The selected side name
     */
    public String getSelectedSide(int nbSide) {
        switch (nbSide) {
            case 1: return "Riz";
            case 2: return "Frites";
            case 3: return "Légumes frais";
            default: return "Rien";
        }
    }

    /**
     * Run asking process for a side
     */
    public void runSide() {
        int nbSide = -1;

        do {
            displaySides(this.nbMenu);
            nbSide = getIntFromTheKeyboard(nbSide);
        } while (nbSide < 0 || nbSide > 3);

            System.out.println("Vous avez choisi comme accompagnement : " + getSelectedSide(nbSide));
            this.nbSide = nbSide;
    }

    /**
     * Display drinks depending on the selected menu:
     * Drink is not offered for the menu beef
     * @param nbMenu The menu number
     */
    public void displayDrinks(int nbMenu) {
        System.out.println("\nBOISSONS : ");

        if (nbMenu == 2) {
            System.out.println("Désolé, aucune boisson n'est proposée avec le menu n° " + nbMenu);
        } else {
            System.out.println("0. Aucune");
            System.out.println("1. Eau plate");
            System.out.println("2. Eau gazeuse");
            System.out.println("3. Soda");
            System.out.println("Que désirez-vous (entrez le chiffre correspondant) ?");
        }
    }

    /**
     * Get the selected drink
     * @param nbDrink The drink number
     * @return The selected drink
     */
    public String getSelectedDrink(int nbDrink) {
        switch (nbDrink) {
            case 1: return "Eau plate";
            case 2: return "Eau gazeuse";
            case 3: return "Soda";
            default: return "Rien";
        }
    }

    /**
     * Run asking process for a drink
     */
    public void runDrink() {
        int nbDrink = -1;

        do {
            displayDrinks(this.nbMenu);
            if (this.nbMenu == 2) { // No drink is offered for the menu beef (n° 2)
                break;
            }
            nbDrink = getIntFromTheKeyboard(nbDrink);
        } while (nbDrink < 0 || nbDrink > 3);

        System.out.println("Vous avez choisi comme boisson : " + getSelectedDrink(nbDrink));
        this.nbDrink = nbDrink;
    }

    private int getIntFromTheKeyboard(int nbDrink) {
        Scanner sc = new Scanner(System.in);

        try {
            nbDrink = sc.nextInt();

            if (nbDrink < 0 || nbDrink > 3) {
                System.out.println("Vous devez choisir un entier compris entre 0 et 3 !");
            }
        } catch (InputMismatchException e) {
            System.out.println("Vous devez choisir un entier compris entre 0 et 3 !");
        }
        return nbDrink;
    }

    /**
     * Run order and save it in a CSV file
     */
    public void orderFood() {
        runMenu();
        runSide();
        runDrink();

        // Save the order in a CSV file
        Path path = Paths.get(CSV_FILE_NAME);
        try {
            Files.write(path, String.format(this.nbMenu + "," + this.nbSide + "," + this.nbDrink + "%n").getBytes(), APPEND);
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite. Vérifier si le chemin vers le fichier est correct.\t" + e);
        }
        System.out.println("\nVotre commande a bien été renvoyée en cuisine.");
    }

    /**
     * Read orders from a CSV file
     */
    public void getOrderFromCSV() {
        Path pathToOrders = Paths.get(CSV_FILE_NAME);
        List<String> orders = null;

        try {
            orders = Files.readAllLines(pathToOrders);
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite. Vérifier si le chemin vers le fichier est correct.\t" + e);
            return;
        }

        System.out.println("Commandes enregistrées : ");

        for (int i = 1; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }
}
