package fr.kata;

import java.math.BigDecimal;

public class Invoice {

    private final Integer invoiceNumber;
    private final Command command;

    private final BigDecimal taxesAmount;
    private final BigDecimal totalAmount;

    public Invoice(Integer invoiceNumber, Command command, BigDecimal taxesAmount, BigDecimal totalAmount) {
        this.invoiceNumber = invoiceNumber;
        this.command = command;
        this.taxesAmount = taxesAmount;
        this.totalAmount = totalAmount;
    }

    public void invoiceDetails() {
        command.getCommandProducts().forEach(p -> System.out.println(p.toString() + " : "+
                BigDecimal.valueOf(p.getQuantity()).multiply(p.computeProductTTCPrice()) + " TTC"));

        System.out.println("Montant des taxes : "+taxesAmount);
        System.out.println("Total : "+totalAmount);
    }
}
