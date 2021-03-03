import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    Collection<Person> persons = new ArrayList<>();
    for (int i = 0; i < 10_000_000; i++) {
      persons.add(new Person(names.get(new Random().nextInt(names.size())),
                      families.get(new Random().nextInt(families.size())),
                      new Random().nextInt(100),
                      Sex.values()[new Random().nextInt(Sex.values().length)],
                      Education.values()[new Random().nextInt(Education.values().length)]));
    }
    //Несовершеннолетние
    Stream<Person> personStream = persons.stream();
    long count = personStream.filter(x -> x.getAge() < 18)
            .count();
    System.out.println("Кол-во несовершеннолетних: " + count);

    //Призывники
    Stream<Person> conscriptStream = persons.stream();
    List<String> namePerson = conscriptStream.filter(x -> x.getAge() > 18)
            .filter(x -> x.getAge() < 27)
            .map(Person::getName)
            .collect(Collectors.toList());
    for(String name : namePerson){
      System.out.println(name);
    }

    //Работоспособные люди
    Stream<Person> workPersonStream = persons.stream();
    List<Person> workPerson = workPersonStream.filter(x -> (x.getSex().equals(Sex.MAN) && (x.getAge() >= 18 && x.getAge()<=65)) || (x.getSex().equals(Sex.WOMEN) && (x.getAge() >= 18 && x.getAge()<=60)))
            .sorted(Comparator.comparing(Person::getFamily))
            .collect(Collectors.toList());
    for(Person name : workPerson){
      System.out.println(name);
    }
  }
}
