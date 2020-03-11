package ProblemaSDA1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {
    private String name;
    private Trainer trainer;
    private List<Student> students;
    private List<Module> modules;

    public Group(String name, Trainer trainer, List<Student> students) {
        this.name = name;
        this.trainer = trainer;
        this.students = students;
        this.modules = (setModules());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Module> setModules() {
        return Arrays.asList(Module.values());
    }

    @Override
    public String toString() {
        return "Group" + name + "Students list" + students + "Trainer" + trainer + "\n";
    }

    public void addStudent(Student student) throws MaximumNumberOfStudentReached {
        if (students.size() >= 5) {
            throw new MaximumNumberOfStudentReached();
        }
        List<Student> studentsList = new ArrayList<>(students);
        studentsList.add(student);
        students = studentsList;
    }

}
