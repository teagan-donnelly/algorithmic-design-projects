package hw06_spring;

//Teagan  Donnelly
//circle class implementing shape
public class Circle implements Shape {
    private double radius;

    //constructor for circle
    public Circle(double r) {
        this.radius = r;
    }

    //return radius
    public double getRadius() {
        return radius;
    }

    //compute area of circle
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    //compare shapes for bst ordering
    @Override
    public int compareTo(Shape other) {
        double a1 = this.getArea();
        double a2 = other.getArea();

        if (a1 < a2) return -1;
        if (a1 > a2) return 1;

        //circle has highest priority on tie
        if (other instanceof Circle) return 0;
        return -1;
    }

    //check for exact match
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) return false;
        Circle c = (Circle) o;
        return this.radius == c.radius;
    }

    //string for console printing
    @Override
    public String toString() {
        return "Circle Radius: " + radius + " Area: " + getArea();
    }

    //string for file writing
    @Override
    public String toFileString() {
        return "Circle\t" + radius;
    }
}


