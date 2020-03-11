package ProblemaSDA1;

public class Student extends Person {
    private boolean hasBackground;
    private boolean isPresent;

    public Student(String firstName, String lastName, int age, boolean isPresent, boolean hasBackground) {
        super(firstName, lastName, age);
        this.isPresent = isPresent;
        this.hasBackground = hasBackground;
    }


    public boolean isHasBackground() {
        return hasBackground;
    }

    public void setHasBackground(boolean hasBackground) {
        this.hasBackground = hasBackground;
    }

    @Override
    public String toString() {
        return "Student:" + super.toString() + "Age:" + getAge() + "Background:" + hasBackground + "Prezenta:" + isPresent +"\n";
    }
}
