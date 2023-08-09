import model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        final String PATH = "people.csv";
        List<Person> listPeople = readCsvAllPeople("people.csv");
        List<Person> listPeopleFilter = new ArrayList<>();
        int op = 0;
        do {
            System.out.println("""
                    1.- MOSTRAR TODAS LAS PERSONAS
                    2.- APARTADO A
                    3.- APARTADO B
                    4.- APARTADO C
                    5.- APARTADO D
                    6.- SALIR""");
            System.out.println("Elija una opcion: ");
            op = Integer.parseInt(s.nextLine());
            switch (op) {
                case 1:
                    for (Person p:
                         listPeople) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    listPeopleFilter = filterAge25(listPeople);
                    for (Person p:
                         listPeopleFilter) {
                        System.out.println(p);
                    }
                case 3:
                    listPeopleFilter = filterLetterA(listPeople);
                    for (Person p:
                         listPeopleFilter) {
                        System.out.println(p);
                    }
                case 4:
                    filterTownMadrid(filterAge25(listPeople));
                    break;
                case 5:
                    filterTownBarcelona(filterAge25(listPeople));
                    break;
                default:
                    String var8 = "Elija una opcion correcta";
            }
        } while(op != 6);

    }

    public static List<Person> readCsvAllPeople(String path) {
        List<Person> listPeople = new ArrayList();

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            for (String line : lines) {
                String[] data = line.split(":");

                // Asegurarse de que la línea tenga al menos un campo no vacío
                if (data.length >= 1) {
                    String name = data[0].trim();
                    String town = data.length >= 2 ? data[1].trim() : "";
                    int age = data.length >= 3 ? Integer.parseInt(data[2].trim()) : 0;

                    if (name.isEmpty()) {
                        throw new InvalidLineFormatException("Invalid name format: " + line);
                    }

                    listPeople.add(new Person(name, town, age));
                } else {
                    throw new InvalidLineFormatException("Invalid line format: " + line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listPeople;
    }

    public static List<Person> filterAge25(List<Person> people) {
        List<Person> listPeopleFilterAge = new ArrayList();
        for (Person p:
             people) {
            if (p.getAge() < 25){
                listPeopleFilterAge.add(p);
            }
        }

        return listPeopleFilterAge;
    }

    public static List<Person> filterLetterA(List<Person> people) {
        List<Person> listPeopleFilterLetterA = new ArrayList();
        for (Person p:
             people) {
            if (!p.getName().startsWith("A")){
                listPeopleFilterLetterA.add(p);
            }
        }

        return listPeopleFilterLetterA;
    }

    public static void filterTownMadrid(List<Person> people) {
        Stream<Person> peopleStream = people.stream();
        Optional<Person> personFromMadrid = peopleStream.filter((person) -> {
            return person.getTown().equals("Madrid");
        }).findFirst();
        if (personFromMadrid.isEmpty()) {
            System.out.println("No se ha encontrado con ciudad en Madrid");
        } else {
            System.out.println(personFromMadrid.get());
        }

    }

    public static void filterTownBarcelona(List<Person> people) {
        Stream<Person> peopleStream = people.stream();
        Optional<Person> personBarcelona = peopleStream.filter((person) -> {
            return person.getTown().equals("Barcelona");
        }).findFirst();
        if (personBarcelona.isEmpty()) {
            System.out.println("No se encuentra ninguna persona con ciudad en Barcelona");
        } else {
            System.out.println(personBarcelona.get());
        }

    }

    static class InvalidLineFormatException extends RuntimeException {
        public InvalidLineFormatException(String message) {
            super(message);
        }
    }
    }