package ProblemaSDA1;

import java.util.List;

//https://www.linkedin.com/in/bota-sorina-georgiana-81136891/
/*Manually initialize 15 students; 4 groups and 3 trainers;
1.Store all students in a list; all groups in a list; all trainers in a list;
2.Asign a trainer to each group
3.Asign 2/3 students to each group
4.Ensure the fact that a group will only have distinct students (How would you do that?)
5.Ensure the fact that a group will only have a maximum of 2 students; When you try to add a 3th one throw an MaximumNumberOfStudentsReached exception
6.Display all students in a group sorted alphabetically by lastName
7.Display the group with the maximum number of students
8.Display all students younger than 25, from all groups
9.Display all students grouped by trainer that teaches to them (eg. Trainer1 - stud1, stud3, stud4; Trainer2 - stud2, stud 10)
- regardless of the group they're part of (If you were to store this information in a data structure what would you use?)
10.Display all students with previous java knowledge
11.Display the group with the highest number of students with no previous java knowledge
12.Remove all the students younger than 20 from all groups*/
public class Main {
    public static void main(String[] args) {
        System.out.println(Manager.getGroupList());
        System.out.println(Manager.getCurs());
        Manager.displayMaxSize();
        List<Student>studentList=Manager.getStudentList();
        System.out.println(Manager.displayStudents(studentList));
        //daca vrem sa vedem pe grupuri diferite:Manager.displayStudents(group1);
        System.out.println(Manager.showGroupName(Manager.getStudentList1())+Manager.displayStudents(Manager.getStudentList1()));
        System.out.println(Manager.showGroupName(Manager.getStudentList2())+Manager.displayStudents(Manager.getStudentList2()));
        System.out.println(Manager.displayTrainer());
        System.out.println("Punctul 10,11,12:");
        System.out.println(Manager.displayStudBackground(Manager.getStudentList()));
        Group groupWithMaxStudentsWithItBackground = null;
        int maxStudentsWithItBackground = 0;
        System.out.println(groupWithMaxStudentsWithItBackground);
        System.out.println("Students with no java knowledge: " + maxStudentsWithItBackground);
        System.out.println(Manager.removeStudents(studentList));
    }
}
