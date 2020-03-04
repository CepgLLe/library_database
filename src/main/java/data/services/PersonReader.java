package data.services;

import data.obj.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class PersonReader {

    private TreeSet<Person> personTreeSet;
    private BufferedReader br;

    public PersonReader(Path path) throws IOException {
        personTreeSet = new TreeSet<>();
        this.br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()), StandardCharsets.UTF_8));
    }

    public void read() throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            addPersonTreeSet(line);
        }
        close();
        System.out.println("Persons list was read.");
    }

    private void addPersonTreeSet(String line) {
        personTreeSet.add(new Person(
                Integer.parseInt(getParam(line, "id=", ',')),
                getParam(line, "lastName=", ','),
                getParam(line, "firstName=", ','),
                getParam(line, "fatherName=", ','),
                getParam(line, "sex=", ','),
                LocalDate.parse(getParam(line, "birthDate=", '}'),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        ));
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

    public TreeSet<Person> getPersonTreeSet() {
        return personTreeSet;
    }

    // A method return person by ID but only after reading a file.
    public Person getPersonById(int id) {
        Person person = null;
        for (Person p : personTreeSet) {
            if (p.getId() == id) person = p;
        }
        return person;
    }
}
