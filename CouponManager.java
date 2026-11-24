import java.io.*;
import java.util.*;

public class CouponManager {
    HashMap<String, Coupon> hashMap = new HashMap<>();
    CouponBST bst = new CouponBST();

    // 1. Add Coupon
    public void addCoupon(Coupon c) {
        if (hashMap.containsKey(c.code)) {
            System.out.println("Duplicate code rejected.");
            return;
        }
        hashMap.put(c.code, c);
        bst.insert(c);
        System.out.println("Coupon added: " + c.code + " | " + c.value + (c.type.equals("PERCENTAGE") ? "%" : " Flat"));
    }

    // 2. Validate & Apply
    public double applyCoupon(String code, double bill) {
        if (!hashMap.containsKey(code)) {
            System.out.println("Coupon " + code + " not found.");
            return bill;
        }

        Coupon c = hashMap.get(code);
        
        // Basic validation
        if (bill < c.minBill) {
            System.out.println("Bill doesn't meet minimum amount.");
            return bill;
        }
        if (c.usageCount >= c.maxUses) {
            System.out.println("Coupon usage limit reached.");
            return bill;
        }

        double discount = 0;
        if (c.type.equals("PERCENTAGE")) {
            discount = (c.value / 100.0) * bill;
        } else {
            discount = c.value;
        }

        // Ensure discount doesn't exceed bill
        if (discount > bill) discount = bill;

        c.usageCount++;
        double finalBill = bill - discount;
        System.out.println("Discount: " + (int)discount + " | Final bill: " + (int)finalBill);
        return finalBill;
    }

    // 3. Greedy Scenario (Choose best)
    public void getBestDiscount(double bill, String code1, String code2) {
        double discount1 = calculateDiscount(code1, bill);
        double discount2 = calculateDiscount(code2, bill);

        if (discount1 == 0 && discount2 == 0) {
            System.out.println("No valid coupons found.");
        } else if (discount1 >= discount2) {
            applyCoupon(code1, bill);
            System.out.println("(Chosen from " + code1 + " and " + code2 + ")");
        } else {
            applyCoupon(code2, bill);
            System.out.println("(Chosen from " + code1 + " and " + code2 + ")");
        }
    }

    private double calculateDiscount(String code, double bill) {
        if (!hashMap.containsKey(code)) return 0;
        Coupon c = hashMap.get(code);
        if (bill < c.minBill || c.usageCount >= c.maxUses) return 0;
        if (c.type.equals("PERCENTAGE")) return Math.min((c.value / 100.0) * bill, bill);
        return Math.min(c.value, bill);
    }

    // 4. File Handling
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Coupon c : hashMap.values()) {
                writer.println(c.code + "," + c.type + "," + c.value + "," + c.minBill + "," + c.maxUses + "," + c.usageCount + "," + c.startDate + "," + c.endDate);
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Coupon c = new Coupon(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4]), parts[6], parts[7]);
                c.usageCount = Integer.parseInt(parts[5]);
                hashMap.put(c.code, c);
                bst.insert(c);
            }
        } catch (Exception e) {
            System.out.println("No data file found. Starting fresh.");
        }
    }
}