public class Coupon {
    String code;
    String type; 
    double value;
    double minBill;
    int maxUses;
    int usageCount;
    String startDate;
    String endDate;

    public Coupon(String code, String type, double value, double minBill, int maxUses, String startDate, String endDate) {
        this.code = code;
        this.type = type;
        this.value = value;
        this.minBill = minBill;
        this.maxUses = maxUses;
        this.usageCount = 0;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}