package hw06_spring;

//teagan donnelly

//interface for common shape behavior
public interface Shape extends Comparable<Shape> {

    //return area of shape
    double getArea();

    //return file formatted string
    String toFileString();
}


