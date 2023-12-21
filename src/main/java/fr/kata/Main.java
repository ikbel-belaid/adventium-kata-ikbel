package fr.kata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        firstInvoice();
        secondInvoice();
        thirdInvoice();
    }

    private static void firstInvoice() {
        System.out.println("Input 1");
        List<ProductLine> listProductLine1 = new ArrayList<>();
        ProductLine p1 = new ProductLine(ProductType.BOOKS,"livre", new BigDecimal("12.49"),
                2, Boolean.FALSE);
        ProductLine p2 = new ProductLine(ProductType.OTHERS,"CD musical", new BigDecimal("14.99"),
                1, Boolean.FALSE);
        ProductLine p3 = new ProductLine(ProductType.FOOD,"barre de chocolat", new BigDecimal("0.85"),
                3, Boolean.FALSE);
        listProductLine1.add(p1);
        listProductLine1.add(p2);
        listProductLine1.add(p3);
        Command c1 = new Command(1, listProductLine1);
        c1.commandDetails();

        System.out.println("Output 1");
        Invoice i1 = new Invoice(1, c1, c1.computeTaxesAmount(), c1.computeTotalAmount());
        i1.invoiceDetails();
    }

    private static void secondInvoice() {
        System.out.println("**********************************************************************");
        System.out.println("Input 2");
        List<ProductLine> listProductL2ine = new ArrayList<>();
        ProductLine p4 = new ProductLine(ProductType.FOOD,"boîtes de chocolats importée", new BigDecimal("10"),
                2, Boolean.TRUE);
        ProductLine p5 = new ProductLine(ProductType.OTHERS,"flacons de parfum importé", new BigDecimal("47.50"),
                3, Boolean.TRUE);
        listProductL2ine.add(p4);
        listProductL2ine.add(p5);
        Command c2 = new Command(2, listProductL2ine);
        c2.commandDetails();

        System.out.println("Output 2");
        Invoice i2 = new Invoice(2, c2, c2.computeTaxesAmount(), c2.computeTotalAmount());
        i2.invoiceDetails();
    }

    private static void thirdInvoice() {
        System.out.println("**********************************************************************");
        System.out.println("Input 3");
        List<ProductLine> listProductLine3 = new ArrayList<>();
        ProductLine p6 = new ProductLine(ProductType.OTHERS,"flacons de parfum importé", new BigDecimal("27.99"),
                2, Boolean.TRUE);
        ProductLine p7 = new ProductLine(ProductType.OTHERS,"flacon de parfum", new BigDecimal("18.99"),
                1, Boolean.FALSE);
        ProductLine p8 = new ProductLine(ProductType.DRUGS,"boîtes de pilules contre la migraine", new BigDecimal("9.75"),
                3, Boolean.FALSE);
        ProductLine p9 = new ProductLine(ProductType.FOOD,"boîtes de chocolats importés", new BigDecimal("11.25"),
                2, Boolean.TRUE);
        listProductLine3.add(p6);
        listProductLine3.add(p7);
        listProductLine3.add(p8);
        listProductLine3.add(p9);
        Command c3 = new Command(3, listProductLine3);
        c3.commandDetails();

        System.out.println("Output 3");
        Invoice i3 = new Invoice(3, c3, c3.computeTaxesAmount(), c3.computeTotalAmount());
        i3.invoiceDetails();
    }
}