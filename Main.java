public class Main {
    public static void main(String[] args) {
        CouponManager manager = new CouponManager();

        System.out.println("--- Running Test Cases ---");

        // Test Case 1: Normal Input
        Coupon c1 = new Coupon("SAVE10", "PERCENTAGE", 10, 0, 100, "2026-01-01", "2026-12-31");
        manager.addCoupon(c1);

        // Test Case 2: Boundary / Minimum Input
        manager.applyCoupon("SAVE10", 100);

        // Test Case 3: Invalid Input
        manager.applyCoupon("BAD99", 100);

        // Test Case 4: Different Valid Scenario (Greedy)
        Coupon c2 = new Coupon("FLAT15", "FLAT", 15, 0, 100, "2026-01-01", "2026-12-31");
        manager.addCoupon(c2);
        manager.getBestDiscount(100, "SAVE10", "FLAT15");

        // Save Data
        manager.saveToFile("coupons.txt");
        System.out.println("Data saved to file.");
    }
}











