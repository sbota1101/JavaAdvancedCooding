package ProblemaSDA1;

public class Curs {
    private Module module;
    private Group group;

    public Curs(Module module, Group group) {
        this.module = module;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Modul:"+module+" date:"+module.getDate()+group;
    }
}
