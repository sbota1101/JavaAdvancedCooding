package ProblemaSDA2;

public enum MotorCycleShape implements Shape{
    CHOPPER,
    CRUISER,
    ENDURO
    ;

    @Override
    public String displayShape() {
        return CHOPPER.toString()+CRUISER.toString()+ENDURO.toString();
    }
}
