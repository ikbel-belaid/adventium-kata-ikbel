package fr.kata;

import java.math.BigDecimal;
import java.util.List;

public class Command {
    private Integer commandNumber;
    private List<ProductLine> commandProductLines;

    public Command(Integer commandNumber, List<ProductLine> commandProductLines) {
        this.commandNumber = commandNumber;
        this.commandProductLines = commandProductLines;
    }

    public List<ProductLine> getCommandProducts() {
        return commandProductLines;
    }

    public BigDecimal computeTaxesAmount(){
        return commandProductLines.stream()
                .map(p -> p.computeProductTaxesAmount().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(new BigDecimal("0"), BigDecimal::add);
    }

    public BigDecimal computeTotalAmount(){
        return commandProductLines.stream()
                .map(p -> p.computeProductTTCPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(new BigDecimal("0"), BigDecimal::add);
    }

    public void commandDetails() {
        commandProductLines.forEach(p -> System.out.println(p.toString()));
    }
}
