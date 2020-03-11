package ProblemaSDA2;

import jdk.nashorn.internal.ir.CallNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/*
1.Read vehicles.txt and create objects of the proper type
2.Count the number of cars, motorcycles, tractors
3.Count how many vehicles of each brand are there
4.Sort the cars by price
5.Sort the choppers by top speed
6.Display each category of vehicles in separate files*/

public class Main {

    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<Motorcycles> motorcycles = new ArrayList<>();
    private static List<Tractor> tractors = new ArrayList<>();
    private static boolean exceptionFound = false;
    private static final String FILEPATHS = "C:\\Users\\Sorina\\IdeaProjects\\CodingAdvanced\\src\\main\\resources\\";

    public static void main(String[] args) throws IOException {
        readFile();
        if (exceptionFound) {
            System.out.println("vehicle not found");
            // throw new UnknownLineException();
        }
        System.out.println(vehicles);
        buildCategory();
        countBrands();
        System.out.println(sortByPriceCars());

        System.out.println(sortByMotorSpeed());
        writeFile();
    }

    //o metoada de citit fisierul:
    public static void readFile() {
        Path path = Paths.get("C:\\Users\\Sorina\\IdeaProjects\\CodingAdvanced\\src\\main\\resources\\Vehicles.txt");
        // List<String>lines=new ArrayList<>();
        //in loc de List punem Stream care este autoclosable:asa se inchide automat fisierul:nu mai fol cu stream
        // try (Stream<String> fileLines= Files.lines(path)){
        //       lines=fileLines.collect(Collectors.toList());
        try {
            List<String> fileLines = Files.readAllLines(path);
            for (String line : fileLines) {
                Vehicle vehicle = convertLineToVehicle(line);
                if (vehicle != null) {
                    vehicles.add(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception " + " " + e.getMessage());
        }

    }

    public static Vehicle convertLineToVehicle(String line) {
        String[] words = line.split(", ");
        Vehicle vehicle = null;
        switch (words[0]) {
            case "Car":
                vehicle = convertLineToCar(words);
                break;
            case "Motorcycle":
                vehicle = convertLineToMotorcycle(words);
                break;
            case "Tractor":
                vehicle = convertLineToTractor(words);
                break;
            default:
                exceptionFound = true;
                break;
            // vehicle=buildVechile(words);
            //throw new UnknownLineException();


        }
        return vehicle;
    }

    public static Car convertLineToCar(String[] words) {
        Car car = new Car();
        car.setBrand(words[1]);
        car.setModel(words[2]);
        car.setPrice(convertStringToInt(words[3]));
        car.setTopSpeed(Integer.parseInt(words[4]));
        car.setTransmission(Transmission.valueOf(words[5]));// pentru ca este un Transmisia este  Enum
        // valueof Stringul coneverteste la o valoare din enumul meu
        car.setCarShape(CarShape.valueOf(words[6]));
        return car;
    }

    public static Motorcycles convertLineToMotorcycle(String[] words) {
        Motorcycles motorcycles = new Motorcycles();
        motorcycles.setBrand(words[1]);
        motorcycles.setModel(words[2]);
        motorcycles.setPrice(convertStringToInt(words[3]));
        motorcycles.setTopSpeed(Integer.parseInt(words[4]));
        motorcycles.setMotorCycleShape(MotorCycleShape.valueOf(words[5]));
        return motorcycles;
    }

    public static Tractor convertLineToTractor(String[] words) {
        Tractor tractor = new Tractor();
        tractor.setBrand(words[1]);
        tractor.setModel(words[2]);
        tractor.setPrice(convertStringToInt(words[3]));
        tractor.setMaxPulledWeight(Integer.parseInt(words[4]));
        return tractor;
    }
    //metoda asta este pt bicicleta

    public static Vehicle buildVechile(String[] words) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(words[1]);
        vehicle.setModel(words[2]);
        vehicle.setPrice(convertStringToInt(words[3]));
        return vehicle;
    }

    public static int convertStringToInt(String word) {
        int value = 0;
        try {
            value = Integer.parseInt(word);
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format at element " + " " + word);
        }
        return value;
    }

    public static void buildCategory() {
        for (Vehicle v : vehicles) {
            if (v instanceof Car) {
                cars.add((Car) v);
            }
            if (v instanceof Motorcycles) {
                motorcycles.add((Motorcycles) v);
            }
            if (v instanceof Tractor) {
                tractors.add((Tractor) v);
            }
        }
        System.out.println("Cars:" + cars.size());
        System.out.println("Motorcycle:" + motorcycles.size());
        System.out.println("Tractors" + tractors.size());
    }

    public static void countBrands() {
        Map<String, Integer> brandMap = new HashMap<>();
        for (Vehicle v : vehicles) {
            String brandName = v.getBrand();
            if (!brandMap.containsKey(brandName)) {
                brandMap.put(brandName, 1);
            } else {

                int value = brandMap.get(brandName);
                brandMap.put(brandName, ++value);
            }
        }
        System.out.println(brandMap);
    }

    public static List<Car> sortByPriceCars() {
        // return cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
        //sau daca vrem cu revers:
        //return cars.stream().sorted(Comparator.comparing(Car::getPrice).reversed()).collect(Collectors.toList());
        //alta metoda:
        return cars.stream().sorted((c1, c2) -> c1.compareTo(c2)).collect(Collectors.toList());
    }

    public static List<Motorcycles> sortByMotorSpeed() {
        return motorcycles.stream().filter(s -> s.getMotorCycleShape().equals(MotorCycleShape.CHOPPER))
                .sorted(Comparator.comparing(Motorcycles::getTopSpeed)).collect(Collectors.toList());
    }

    public static void writeFile() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILEPATHS + "Cars.txt"));
        for (Car c : cars) {
            bufferedWriter.write(c.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        bufferedWriter = new BufferedWriter((new FileWriter(FILEPATHS + "Motorcycle.txt")));
        for (Motorcycles m : motorcycles) {
            bufferedWriter.write(m.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

        bufferedWriter = new BufferedWriter((new FileWriter(FILEPATHS + "Tractors.txt")));
        for (Tractor t : tractors) {
            bufferedWriter.write(t.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

}
