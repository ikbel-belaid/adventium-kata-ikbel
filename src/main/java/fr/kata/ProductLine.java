package fr.kata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductLine {
    private final ProductType productType;
    private final String productName;
    private final BigDecimal productHTPrice;

    private final Integer quantity;
    private final boolean imported;

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
                    .map(t -> roundToNext5(productHTPrice.multiply(BigDecimal.valueOf(t))
                            .divide(BigDecimal.valueOf(100),2, RoundingMode.UP),2))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal computeProductTTCPrice(){

        return productHTPrice.add(computeProductTaxesAmount());
    }


    public static BigDecimal roundToNext5(BigDecimal bigDecimal, int scale) {
        // Get the last digit we need to decide if we have to round to 0, 5 or 10
        int lastDigit = bigDecimal
                .movePointRight(scale)
                .remainder(BigDecimal.TEN).intValue();
        // Setting the Scale to scale - 1 to remove one more digit than we need
        // and then increase the scale to what we want
        BigDecimal result = bigDecimal
                .setScale(scale - 1, RoundingMode.DOWN)
                .setScale(scale, RoundingMode.UNNECESSARY);
        if (lastDigit == 0) {
            // Last digit is a 0 upscaling adds a 0
            return result;
        } else if (lastDigit <= 5) {
            // rounding up to 5
            return result.add(new BigDecimal("5").movePointLeft(scale));
        } else {
            // rounding up to 10
            return result.add(new BigDecimal("1").movePointLeft(scale - 1));
        }
    }

    @Override
    public String toString() {
        return quantity +" "+ productName + " à " + productHTPrice;
    }
}
