package hw06_spring;

//Teagan donnelly

//rectangle class implementing shape
public class Rectangle implements Shape {
    private double length, width;

    //constructor for rectangle
    public Rectangle(double l, double w) {
        this.length = l;
        this.width = w;
    }

    //return length
    public double getLength() { return length; }

    //return width
    public double getWidth() { return width; }

    //compute area of rectangle
    @Override
    public double getArea() {
        return length * width;
    }

    //compare shapes for bst ordering
    @Override
    public int compareTo(Shape other) {
        double a1 = this.getArea();
        double a2 = other.getArea();

        if (a1 < a2) return -1;
        if (a1 > a2) return 1;

        //rectangle priority between circle and triangle
        if (other instanceof Circle) return 1;
        if (other instanceof Rectangle) return 0;
        return -1;
    }

    //check for exact match
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle)) return false;
        Rectangle r = (Rectangle) o;
        return this.length == r.length && this.width == r.width;
    }

    //string for console printing
    @Override
    public String toString() {
        return "Rectangle Length: " + length + " Width: " + width + " Area: " + getArea();
    }

    //string for file writing
    @Override
    public String toFileString() {
        return "Rectangle\t" + length + "\t" + width;
    }
}


