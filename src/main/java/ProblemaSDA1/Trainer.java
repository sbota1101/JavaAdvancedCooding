package ProblemaSDA1;

public class Trainer extends Person {
    private boolean hasCertificate;
    public Trainer(String firstName, String lastName, boolean hasCertificate) {
        super(firstName, lastName);
        this.hasCertificate = hasCertificate;
    }

    public boolean isHasCertificate() {
        return hasCertificate;
    }
    @Override
    public String toString(){
        return "Trainer"+super.toString()+"C" +
                "ertificate:"+hasCertificate+"\n";
    }
}
