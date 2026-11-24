class TreeNode {
    Coupon coupon;
    TreeNode left, right;

    public TreeNode(Coupon coupon) {
        this.coupon = coupon;
    }
}

public class CouponBST {
    TreeNode root;

    // Add to BST
    public void insert(Coupon coupon) {
        root = insertRec(root, coupon);
    }

    private TreeNode insertRec(TreeNode root, Coupon coupon) {
        if (root == null) {
            root = new TreeNode(coupon);
            return root;
        }
        if (coupon.code.compareTo(root.coupon.code) < 0) {
            root.left = insertRec(root.left, coupon);
        } else if (coupon.code.compareTo(root.coupon.code) > 0) {
            root.right = insertRec(root.right, coupon);
        }
        return root;
    }

    // Print sorted (for reports)
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.coupon.code + " - " + root.coupon.type);
            inorderRec(root.right);
        }
    }
}