package ProblemaSDA1;

public enum Module {
    JAVA_HISTORY("2020-02-02"),
    JAVA_FUNDAMENTALS("2020-02-09"),
    JAVA_INTERMEDIATE("2020-02-15"),
    JAVA_ADVANCED("2020-02-23"),
    JAVA_DESIGN_PATTERNS("2020-03-01");

    private String date;

    Module(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
