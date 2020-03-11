package ProblemaSDA1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Manager {
    static List<Group> groupList = new ArrayList<>();
    private static Curs curs;
    private static List<Student> studentList = new ArrayList<>();
    private static List<Student> studentList1;
    private static List<Student> studentList2;

    //getter si setter la studentList:si la fiecare grup pe rand
    public static List<Student> getStudentList1() {
        return studentList1;
    }

    public static List<Student> getStudentList2() {
        return studentList2;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }


    static {
        //facem un bloc static,se poate fie asa si fie puteam sa punem static la clasa sus


        // List<Student> studentList1 = new ArrayList<>();
        // List<Student> studentList2 = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Sorina\\IdeaProjects\\CodingAdvanced\\src\\main\\resources\\Student.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] words = line.split(" ");
                String firstName = words[0];
                String lastName = words[1];
                int age = Integer.parseInt(words[2]);
                boolean isPresent = Boolean.parseBoolean(words[3]);
                boolean hasBackground = Boolean.parseBoolean(words[4]);
                Student student = new Student(firstName, lastName, age, isPresent, hasBackground);

                List<String> firstNames = studentList.stream().map(Student::getFirstName).collect(Collectors.toList());
                //alta alternativa fara stream:cu un simplu enhanced for
                //   List<String>firstNames=new ArrayList<>();
                //   for(Student student:studentList){
                //      firstNames.add(student.getFirstName());
                //  }
                if (!firstNames.contains(student.getFirstName())) {
                    studentList.add(student);
                }

                //o alternativa la ce am scris mai sus cu optinals:
             /*   Optional<Student> s = Optional.ofNullable(studentList.stream().filter(stud -> stud.getFirstName()
                        .equals(firstName) && stud.getLastName().equals(lastName)).findFirst().orElse(null));
                if(s==null){
                    studentList.add(student);
                }*/
                line = bufferedReader.readLine();

            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();-asta e ceva generat de java,ca sa fie mai sugestiv punem altceva nu mesaj din care sa ne dam seama
            System.out.println("File not found");
        } catch (IOException e) {
            //  e.printStackTrace();
            System.out.println("I/O Exception");
        }
        //pt ca am citit din fisier,partea de jos nu se mai aplica:
       /* Student student1 = new Student("Georgiana", "Radoi", 26,true);
        Student student2 = new Student("Vasile", "Pablo", 30,false);
        Student student3 = new Student("Ion", "Creanga", 20,true);
        Student student4 = new Student("Ioana", "Virus", 25,false);
        student4.setHasBackground(true);
        // creem doua liste de studenti lista de studenti
        List<Student> studentsList1 = new ArrayList<Student>();
        studentsList1.add(student1);
        studentsList1.add(student2);
        List<Student> studentsList2 = new ArrayList<Student>();
        studentsList2.add(student3);
        studentsList2.add(student4);
        Trainer trainer1 = new Trainer("Cristian","Hasovan",true);
        Trainer trainer2 = new Trainer("Bianca","Rites",true);
        Group group1=new Group("Grupa6",trainer1,studentsList1);
        Group group2=new Group("Grupa7",trainer2,studentsList2);
        groupList.add(group1);
        groupList.add(group2);
        for (Student student : studentList) {
            if (student.getFirstName().startsWith("I")) {
                studentsList1.add(student);
            } else {
                studentsList2.add(student);
            }*/
        //a 2-a var mai complicata studentsList1=studentList.stream().limit(2).forEach(student -> studentsList1.add(student);
        //a 3-a var.cea mai usoara cu sublist:
        int size = studentList.size();
        studentList1 = studentList.subList(0, size / 2);
        studentList2 = studentList.subList(size / 2, size);
        studentList1 = studentList1.stream().distinct().collect(Collectors.toList());
        //Trainer trainer1 = new Trainer("Cristian", "Arsovan", true);
        //Trainer trainer2 = new Trainer("Bianca", "Rites", true);
        //citit din fisier trainerii cu a 2-a modalitate:
        Path path = Paths.get("C:\\Users\\Sorina\\IdeaProjects\\CodingAdvanced\\src\\main\\resources\\Trainer.txt");
        List<Trainer> trainerList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                List<String> words = Arrays.asList(line.split(" "));
                Trainer trainer = new Trainer(words.get(0), words.get(1), Boolean.parseBoolean(words.get(2)));
                trainerList.add(trainer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       /* Random random=new Random();
      //  random.nextInt();
        int number = random.nextInt(2);
        int number2 = random.nextInt(2);
        while(number == number2) {
            number2 = random.nextInt(2);
        }*/
        Student student1 = new Student("Mihai", "Eminescu", 26, true, false);
        Group group1 = new Group("Buhai", trainerList.get(0), studentList1);
        studentList2.stream().sorted(Comparator.comparing(Student::getLastName))
                .forEach(s -> System.out.println(s.getLastName()));
        try {
            group1.addStudent(student1);
        } catch (MaximumNumberOfStudentReached maximumNumberOfStudentReached) {
            maximumNumberOfStudentReached.printStackTrace();
        }
        Collections.shuffle(trainerList);
        Group group2 = new Group("Naparca", trainerList.get(0), studentList2);
        Group group3 = new Group("TM06", trainerList.get(0), Arrays.asList(student1));
        groupList.add(group1);
        groupList.add(group2);
        groupList.add(group3);
        curs = new Curs(Module.JAVA_ADVANCED, group1);
    }

    public static List<Group> getGroupList() {
        return groupList;
    }

    public static void setGroupList(List<Group> groupList) {
        Manager.groupList = groupList;
    }

    public static Curs getCurs() {
        return curs;
    }

    public static void displayMaxSize() {
        Map<String, Integer> map = new HashMap<>();
        for (Group g : getGroupList()) {
            map.put(g.getName(), g.getStudents().size());
        }
        int max = 0;
        String groupName = " ";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                groupName = entry.getKey();
            }
        }

        System.out.println("Grupul maxim" + groupName);
    }

    public static List<String> displayStudents(List<Student> studentList) {
        return studentList.stream().filter(s -> s.getAge() > 25)
                .map(Student::getFirstName).collect(Collectors.toList());

    }

    public static List<String> displayStudBackground(List<Student> studentList) {
        return studentList.stream().filter(s -> s.isHasBackground())
                .map(Student::getFirstName).collect(Collectors.toList());
    }

    public static void displayMaxGroupSizeWithoutBackground(List<Student> studentList) {
        Group groupWithMaxStudentsWithItBackground = null;
        int maxStudentsWithItBackground = 0;
        for (Group g : groupList) {
            int countStudentsWithNoItBackground = 0;
            for (Student s : g.getStudents()) {
                if (!s.isHasBackground()) {
                    countStudentsWithNoItBackground++;
                }
            }
            if (countStudentsWithNoItBackground > maxStudentsWithItBackground) {
                groupWithMaxStudentsWithItBackground = g;
                maxStudentsWithItBackground = countStudentsWithNoItBackground;
            }
        }

    }

        public static String showGroupName (List < Student > students) {
            String groupName = " ";
            for (Group g : getGroupList()) {
                if (g.getStudents().equals(students)) {
                    groupName = g.getName();
                }
            }
            return groupName;
        }
        public static Map<String, Set<Student>> displayTrainer () {
            Map<String, Set<Student>> trainersMap = new HashMap<>();
            for (Group g : getGroupList()) {
                String trainerName = g.getTrainer().getFirstName();
                if (trainersMap.get(trainerName) == null) {
                    Set<Student> students = new HashSet<>(g.getStudents());
                    trainersMap.put(trainerName, students);
                } else {
                    trainersMap.get(trainerName).addAll(new HashSet<Student>(g.getStudents()));
                }
            }
            return trainersMap;
        }
        public static List<Student> removeStudents (List < Student > studentList) {
            List<String> names = displayStudents(studentList);
            //studentList.removeAll()
            return studentList.stream().filter(s -> names.contains(s.getFirstName()))
                    .collect(Collectors.toList());

        }
    }


