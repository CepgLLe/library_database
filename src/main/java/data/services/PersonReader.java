package data.services;

import data.obj.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

public class PersonReader {

    private TreeMap<Integer, Person> personTreeMap;
    private BufferedReader br;

    public PersonReader(Path path) throws IOException {
        personTreeMap = new TreeMap<>(Integer::compare);
        this.br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()), StandardCharsets.UTF_8));
    }

    public void read() throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            addPersonTreeMap(line);
        }
        close();
        System.out.println("Persons list was read.");
    }

    private void addPersonTreeMap(String line) {
        Person person = new Person(
                Integer.parseInt(getParam(line, "id=", ',')),
                getParam(line, "lastName=", ','),
                getParam(line, "firstName=", ','),
                getParam(line, "fatherName=", ','),
                getParam(line, "sex=", ','),
                LocalDate.parse(getParam(line, "birthDate=", '}'),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        personTreeMap.put(person.getId(), person);
//        System.out.println("Person added to \"data_list\"");
    }

    private String getParam(String line, String strBegin, char charEnd) {
        int begin = line.indexOf(strBegin) + strBegin.length();
        int end = line.indexOf(charEnd, begin);

        return line.substring(begin, end);
    }

    public void close() throws IOException {
        br.close();
    }

    public TreeMap<Integer, Person> getPersonTreeMap() {
        return personTreeMap;
    }

    // A method return person by ID but only after reading a file.
    public Person getPersonById(int id) {
        return personTreeMap.get(id);
    }
}
