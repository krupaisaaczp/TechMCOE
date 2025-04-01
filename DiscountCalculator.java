public class DiscountCalculator {
    public static void main(String[] args) {
        // Test different scenarios
        double[] amounts = {80, 150, 500, 1000, 2500};
        boolean[] memberStatuses = {false, true};
        String[] promoCode = {"NONE", "SAVE20", "FIRST10"};
        
        for (double amount : amounts) {
            for (boolean isMember : memberStatuses) {
                for (String promo : promoCode) {
                    double finalPrice = calculateDiscount(amount, isMember, promo);
                    System.out.printf("Amount: $%.2f, Member: %s, PromoCode: %s, Final Price: $%.2f\n", 
                                       amount, isMember, promo, finalPrice);
                }
            }
        }
    }
    
    public static double calculateDiscount(double amount, boolean isMember, String promoCode) {
        double discount = 0;
        
        // Apply bulk purchase discount
        if (amount >= 2000) {
            discount = amount * 0.15; // 15% discount
        } else if (amount >= 1000) {
            discount = amount * 0.10; // 10% discount
        } else if (amount >= 500) {
            discount = amount * 0.05; // 5% discount
        }
        
        // Apply membership discount (after bulk discount)
        double afterBulkDiscount = amount - discount;
        if (isMember) {
            double memberDiscount = afterBulkDiscount * 0.05;
            discount += memberDiscount;
        }
        
        // Apply promotional code (after all other discounts)
        double afterDiscounts = amount - discount;
        if (promoCode.equals("SAVE20") && amount >= 100) {
            double promoDiscount = 20; // Flat $20 off
            discount += promoDiscount;
        } else if (promoCode.equals("FIRST10")) {
            double promoDiscount = afterDiscounts * 0.10;
            discount += promoDiscount;
        }
        
        // Ensure discount doesn't exceed 25% of original amount
        if (discount > amount * 0.25) {
            discount = amount * 0.25;
        }
        
        return amount - discount;
    }
}
