package utils;

import dao.DefaultGeneralDao;
import entities.Order;

import java.util.Scanner;

public class Context {

    private Scanner scanner;
    private DefaultGeneralDao generalDao;
    private Order order;

    public Context(Scanner scanner, DefaultGeneralDao generalDao, Order order) {
        this.scanner = scanner;
        this.generalDao = generalDao;
        this.order = order;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public DefaultGeneralDao getStorage() {
        return generalDao;
    }

    public Order getOrder() {
        return order;
    }
}
