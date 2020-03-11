package ProblemaSDA2;

public enum CarShape implements Shape {
    COUPE,
    SEDAN,
    WAGON
    ;
    @Override
    public String displayShape() {
        return COUPE.toString()+SEDAN.toString()+WAGON.toString();
    }
}
