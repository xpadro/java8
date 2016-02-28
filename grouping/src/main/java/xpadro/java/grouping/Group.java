package main.java.xpadro.java.grouping;

import main.java.xpadro.java.grouping.model.Person;
import main.java.xpadro.java.grouping.model.Pet;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;

public class Group {

    public static void main(String[] args) {
        Group group = new Group();

        Person person1 = new Person("John", "USA", "NYC", new Pet("Max", 5));
        Person person2 = new Person("Steve", "UK", "London", new Pet("Lucy", 8));
        Person person3 = new Person("Anna", "USA", "NYC", new Pet("Buddy", 12));
        Person person4 = new Person("Mike", "USA", "Chicago", new Pet("Duke", 10));

        List<Person> persons = Arrays.asList(person1, person2, person3, person4);

        group.singleLevelGrouping(persons);
        group.twoLevelGrouping(persons);
        group.threeLevelGrouping(persons);
    }

    //Group persons by country
    public void singleLevelGrouping(List<Person> persons) {
        final Map<String, List<Person>> personsByCountry = persons.stream().collect(groupingBy(Person::getCountry));

        System.out.println("Persons in USA: " + personsByCountry.get("USA"));
    }

    //Group persons by country and city
    public void twoLevelGrouping(List<Person> persons) {
        final Map<String, Map<String, List<Person>>> personsByCountryAndCity = persons.stream().collect(
                groupingBy(Person::getCountry,
                        groupingBy(Person::getCity
                        )
                )
        );
        System.out.println("Persons living in London: " + personsByCountryAndCity.get("UK").get("London").size());
    }

    //Group persons by country, city and pet name
    public void threeLevelGrouping(List<Person> persons) {
        final Map<String, Map<String, Map<String, List<Person>>>> personsByCountryCityAndPetName = persons.stream().collect(
                groupingBy(Person::getCountry,
                        groupByCityAndPetName()
                )
        );
        System.out.println("Persons whose pet is named 'Max' and live in NY: " +
                personsByCountryCityAndPetName.get("USA").get("NYC").get("Max").size());
    }

    private Collector<Person, ?, Map<String, Map<String, List<Person>>>> groupByCityAndPetName() {
        return groupingBy(Person::getCity, groupingBy(p -> p.getPet().getName()));
    }
}