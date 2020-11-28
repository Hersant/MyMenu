package com.bse.restaurant;

public class Order {

    /**
     * Display the available menus in the restaurant.
     */
    public void displayMenus() {
        System.out.println("Menu : ");
        System.out.println("1. Poulet");
        System.out.println("2. Boeuf");
        System.out.println("3. Végétarien");
        System.out.println("Que désirez-vous (entrez le chiffre correspondant) ?");
    }

    /**
     * Display the selected menu.
     * @param menuNumber The number of the selected menu
     * @return The name of the selected menu
     */
    public String displaySelectedMenu(int menuNumber) {
        if (menuNumber == 1) {
            return "Poulet";
        } else if (menuNumber == 2) {
            return "Boeuf";
        } else {
            return "Végétarien";
        }
    }
}
