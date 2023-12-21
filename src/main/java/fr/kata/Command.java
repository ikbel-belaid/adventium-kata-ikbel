package fr.kata;

import java.math.BigDecimal;
import java.util.List;

public class Command {
    private final Integer commandNumber;
    private final List<ProductLine> commandProductLines;

    public Command(Integer commandNumber, List<ProductLine> commandProductLines) {
        this.commandNumber = commandNumber;
        this.commandProductLines = commandProductLines;
    }

    public List<ProductLine> getCommandProductLines() {

        return commandProductLines;
    }

    public BigDecimal computeTaxesAmount(){
        return commandProductLines.stream()
                .map(p -> p.computeProductTaxesAmount().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal computeTotalAmount(){
        return commandProductLines.stream()
                .map(p -> p.computeProductTTCPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void commandDetails() {
        commandProductLines.forEach(p -> System.out.println(p.toString()));
    }
}
