package ProblemaSDA2;

public class Car extends Auto implements Comparable<Car> {
    private CarShape carShape;
    private Transmission transmission;

    public CarShape getCarShape() {
        return carShape;
    }

    public void setCarShape(CarShape carShape) {
        this.carShape = carShape;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car"+super.toString()+"CarShape"+carShape+"Transmission"+transmission;
    }

    @Override
    public int compareTo(Car o) {
        return this.getPrice()-o.getPrice();
    }
}
