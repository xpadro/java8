package xpadro.java.grouping;

import xpadro.java.grouping.model.Person;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;

public class Group extends AbstractGroup {

    public static void main(String[] args) {
        Group example = new Group();

        List<Person> persons = buildPersonsList();

        example.singleLevelGrouping(persons);
        example.twoLevelGrouping(persons);
        example.threeLevelGrouping(persons);
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