package ProblemaSDA2;

public class Auto extends Vehicle {
    private int topSpeed;

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    @Override
    public String toString() {
        return super.toString()+"TopSpeed"+topSpeed+"\n";
    }
}
