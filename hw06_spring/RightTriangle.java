package hw06_spring;

//teagan donnelly

//right triangle class implementing shape
public class RightTriangle implements Shape {
    private double base, height;

    //constructor for triangle
    public RightTriangle(double b, double h) {
        this.base = b;
        this.height = h;
    }

    //return base
    public double getBase() { return base; }

    //return height
    public double getHeight() { return height; }

    //compute area of triangle
    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    //compare shapes for bst ordering
    @Override
    public int compareTo(Shape other) {
        double a1 = this.getArea();
        double a2 = other.getArea();

        if (a1 < a2) return -1;
        if (a1 > a2) return 1;

        //triangle has lowest priority on tie
        if (other instanceof RightTriangle) return 0;
        return 1;
    }

    //check for exact match
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RightTriangle)) return false;
        RightTriangle t = (RightTriangle) o;
        return this.base == t.base && this.height == t.height;
    }

    //string for console printing
    @Override
    public String toString() {
        return "Right Triangle Base: " + base + " Height: " + height + " Area: " + getArea();
    }

    //string for file writing
    @Override
    public String toFileString() {
        return "Right Triangle\t" + base + "\t" + height;
    }
}


