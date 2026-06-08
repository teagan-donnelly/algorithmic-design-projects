package hw06_spring;

//imports necessary packages
import java.io.*;
import java.util.*;

public class ShapeTreeManager {
    private ShapeBST tree = new ShapeBST();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("Welcome to the Shape Tree!");

        int choice; //creates varible to store the users choice
        do {
            printMenu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: readFile(); break;
                case 2: printTraversal(); break;
                case 3: addShape(); break;
                case 4: removeShape(); break;
                case 5: searchShape(); break;
                case 6: printMax(); break;
                case 7: removeGreater(); break;
                case 8: writeFile(); break;
            }
        } while (choice != 0);

        System.out.println("Goodbye !");
    }

    //method to print out the menu
    private void printMenu() {
        System.out.println("Enter 1. To Read a Shape Tree from a File.");
        System.out.println("Enter 2. To Print a Tree Traversal to the Console");
        System.out.println("Enter 3. To Add a Shape.");
        System.out.println("Enter 4. To Remove a Shape.");
        System.out.println("Enter 5. To Search for a Shape.");
        System.out.println("Enter 6. To Find the Shape with the Max Area.");
        System.out.println("Enter 7. To Remove All Shapes Greater than an Area.");
        System.out.println("Enter 8. To Print Shape Tree to File.");
        System.out.println("Enter 0. To Quit.");
    }

    private Shape readShapeFromUser(String type) {
        type = type.toLowerCase();

        if (type.equals("circle")) {
            System.out.println("Enter the radius");
            double r = Double.parseDouble(sc.nextLine());
            return new Circle(r);
        }

        if (type.equals("rectangle")) {
            System.out.println("Enter the length followed by the width");
            double l = Double.parseDouble(sc.nextLine());
            double w = Double.parseDouble(sc.nextLine());
            return new Rectangle(l, w);
        }

        if (type.equals("right triangle") || type.equals("triangle")) {
            System.out.println("Enter the base followed by the height");
            double b = Double.parseDouble(sc.nextLine());
            double h = Double.parseDouble(sc.nextLine());
            return new RightTriangle(b, h);
        }

        return null;
    }

    //method for the user to add a shape
    private void addShape() {
        System.out.println("Enter the type of shape to add");
        String type = sc.nextLine();
        Shape s = readShapeFromUser(type);
        if (s != null) tree.insert(s);
    }

    private void removeShape() {
        System.out.println("Enter the type of shape to remove");
        String type = sc.nextLine();
        Shape s = readShapeFromUser(type);
        if (s != null) tree.remove(s);
    }

    private void searchShape() {
        System.out.println("Enter the type of shape to Search");
        String type = sc.nextLine();
        Shape s = readShapeFromUser(type);
        if (s != null)
            System.out.println("Was the shape in the tree? " + tree.search(s));
    }

    private void printMax() {
        Shape s = tree.max();
        if (s != null)
            System.out.println("The shape with the max area " + s);
    }

    private void removeGreater() {
        System.out.println("Enter the maximum area");
        double a = Double.parseDouble(sc.nextLine());
        tree.removeGreaterThan(a);
    }

    private void printTraversal() {
        System.out.println("Which traversal?");
        System.out.println("Enter 1. For Pre-order.");
        System.out.println("Enter 2. For In-order");
        System.out.println("Enter 3. For Post-order");

        int t = Integer.parseInt(sc.nextLine());

        if (t == 1) tree.printPreOrder();
        else if (t == 2) tree.printInOrder();
        else tree.printPostOrder();
    }

    private void readFile() {
        try {
            System.out.println("Enter the file's name");
            String file = sc.nextLine();

            Scanner f = new Scanner(new File(file));

            while (f.hasNextLine()) {
                String[] parts = f.nextLine().split("\t");

                if (parts[0].equals("Circle"))
                    tree.insert(new Circle(Double.parseDouble(parts[1])));

                else if (parts[0].equals("Rectangle"))
                    tree.insert(new Rectangle(
                        Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2])
                    ));

                else if (parts[0].equals("Right Triangle"))
                    tree.insert(new RightTriangle(
                        Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2])
                    ));
            }

            f.close();
            System.out.println("Printing after Reading In-Order");
            tree.printInOrder();

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    private void writeFile() {
        try {
            System.out.println("Enter the file's name");
            String file = sc.nextLine();

            PrintWriter out = new PrintWriter(new File(file));
            writeRec(tree, out);
            out.close();

        } catch (Exception e) {
            System.out.println("Error writing file.");
        }
    }

    private void writeRec(ShapeBST tree, PrintWriter out) {
        writeInOrder(tree, out);
    }

    private void writeInOrder(ShapeBST tree, PrintWriter out) {
        writeInOrderRec(tree, out, getRoot(tree));
    }

    private ShapeNode getRoot(ShapeBST t) {
        try {
            var f = ShapeBST.class.getDeclaredField("root");
            f.setAccessible(true);
            return (ShapeNode) f.get(t);
        } catch (Exception e) {
            return null;
        }
    }

    private void writeInOrderRec(ShapeBST t, PrintWriter out, ShapeNode n) {
        if (n == null) return;
        writeInOrderRec(t, out, n.left);
        out.println(n.data.toFileString());
        writeInOrderRec(t, out, n.right);
    }
}

