package ProblemaSDA2;

public class Motorcycles extends Auto {

    private MotorCycleShape motorCycleShape;


    public MotorCycleShape getMotorCycleShape() {
        return motorCycleShape;
    }

    public void setMotorCycleShape(MotorCycleShape motorCycleShape) {
        this.motorCycleShape = motorCycleShape;
    }

    @Override
    public String toString() {
        return "Motorcycle"+super.toString()+"MotorCycleShape"+motorCycleShape+"\n";
    }
}
