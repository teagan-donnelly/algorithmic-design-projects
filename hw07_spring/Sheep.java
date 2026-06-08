package hw07_spring;

//Teagan Donnelly

public class Sheep implements Comparable<Sheep> {
    private String name;
    private int shearingTime;
    private int arrivalTime;

    //Constructor
    public Sheep(String name, int shearingTime, int arrivalTime) {
        this.name = name;
        this.shearingTime = shearingTime;
        this.arrivalTime = arrivalTime;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getShearingTime() {
        return shearingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public int compareTo(Sheep other) {
        if (this.shearingTime != other.shearingTime) {
            return this.shearingTime - other.shearingTime;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Shear Time: " + shearingTime + ", Arrival Time: " + arrivalTime;
    }
}
