package fr.kata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductLine {
    private ProductType productType;
    private String productName;
    private BigDecimal productHTPrice;

    private Integer quantity;
    private boolean imported;

    public ProductLine(ProductType productType, String productName, BigDecimal productHTPrice, Integer quantity,
                       boolean imported) {
        this.productType = productType;
        this.productName = productName;
        this.productHTPrice = productHTPrice;
        this.quantity = quantity;
        this.imported = imported;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private List<Integer> computeProductTaxesRates(){
        List<Integer> productTaxesRates = new ArrayList<>();
        if(imported){
            productTaxesRates.add(5);
        }
        switch (productType){
            case BOOKS -> productTaxesRates.add(10);
            case OTHERS -> productTaxesRates.add(20);
        }
        return productTaxesRates;
    }

    public BigDecimal computeProductTaxesAmount(){
        List<Integer> productTaxesRates = computeProductTaxesRates();
        if (!productTaxesRates.isEmpty()) {
            return productTaxesRates.stream()
                    .map(t -> productHTPrice.multiply(BigDecimal.valueOf(t)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
                    .reduce(new BigDecimal("0"), BigDecimal::add);
        }
        return new BigDecimal("0");
    }

    public BigDecimal computeProductTTCPrice(){
        return productHTPrice.add(computeProductTaxesAmount());
    }

    @Override
    public String toString() {
        return quantity +" "+ productName + " à " + productHTPrice;
    }
}
