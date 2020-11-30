package com.bse.restaurant;

public class Main {

    public static void main(String[] args) {
        Order order = new Order();
        order.orderFood();
        order.getOrderFromCSV();
    }
}
