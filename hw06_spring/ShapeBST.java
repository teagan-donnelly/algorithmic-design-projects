package hw06_spring;

public class ShapeBST {
    private ShapeNode root;

    public void insert(Shape s) {
        root = insertRec(root, s);
    }

    private ShapeNode insertRec(ShapeNode node, Shape s) {
        if (node == null) return new ShapeNode(s);

        int cmp = s.compareTo(node.data);

        if (cmp < 0)
            node.left = insertRec(node.left, s);
        else if (cmp > 0)
            node.right = insertRec(node.right, s);
        else
            return node; //duplicate

        return node;
    }

    public boolean search(Shape s) {
        return searchRec(root, s);
    }

    private boolean searchRec(ShapeNode node, Shape s) {
        if (node == null) return false;

        int cmp = s.compareTo(node.data);

        if (cmp == 0 && node.data.equals(s)) return true;
        if (cmp < 0) return searchRec(node.left, s);
        return searchRec(node.right, s);
    }

    public void remove(Shape s) {
        root = removeRec(root, s);
    }

    private ShapeNode removeRec(ShapeNode node, Shape s) {
        if (node == null) return null;

        int cmp = s.compareTo(node.data);

        if (cmp < 0)
            node.left = removeRec(node.left, s);
        else if (cmp > 0)
            node.right = removeRec(node.right, s);
        else {
            if (!node.data.equals(s)) return node;

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            ShapeNode min = findMin(node.right);
            node.data = min.data;
            node.right = removeRec(node.right, min.data);
        }
        return node;
    }

    private ShapeNode findMin(ShapeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public Shape max() {
        ShapeNode curr = root;
        if (curr == null) return null;
        while (curr.right != null) curr = curr.right;
        return curr.data;
    }

    public void removeGreaterThan(double area) {
        root = removeGreaterRec(root, area);
    }

    private ShapeNode removeGreaterRec(ShapeNode node, double area) {
        if (node == null) return null;

        if (node.data.getArea() > area)
            return removeGreaterRec(node.left, area);

        node.right = removeGreaterRec(node.right, area);
        return node;
    }

    public void printInOrder() { printInOrderRec(root); }
    private void printInOrderRec(ShapeNode n) {
        if (n == null) return;
        printInOrderRec(n.left);
        System.out.println(n.data);
        printInOrderRec(n.right);
    }

    public void printPreOrder() { printPreOrderRec(root); }
    private void printPreOrderRec(ShapeNode n) {
        if (n == null) return;
        System.out.println(n.data);
        printPreOrderRec(n.left);
        printPreOrderRec(n.right);
    }

    public void printPostOrder() { printPostOrderRec(root); }
    private void printPostOrderRec(ShapeNode n) {
        if (n == null) return;
        printPostOrderRec(n.left);
        printPostOrderRec(n.right);
        System.out.println(n.data);
    }
}
