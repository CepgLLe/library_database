package data.services;

import data.obj.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Change a data saving type (not toString) and add a coder!!!
 * (A decoder will realised in {@link PersonReader})
 */

public class PersonWriter {

    private Path path;
    private BufferedWriter bw;
    private boolean isWritten;

    public PersonWriter(Path path) throws IOException {
        this.path = path;
        this.isWritten = false;

        this.bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(this.path.toFile(), true), StandardCharsets.UTF_8));
    }

    /**
     * A method combine few actions:
     * <ul>
     *     <li> read a file for determinate person ID </li>
     *     <li> create the new person ID </li>
     *     <li> write the new person to file as string </li>
     *     <li> calls a {@link #check(int id, String[] personData)} method which
     *          check that person is written to file </li>
     * </ul>
     * @param personData contains the person data:
     *                   last name, first name, father's name, sex and birth date
     *                   (an order the same)
     * @throws IOException If a file is not found or data is not correct.
     */
    public void write(String[] personData) throws IOException {
        PersonReader pr = new PersonReader(path);
        pr.read();

        int id;
        if (pr.getPersonTreeMap().size() == 0) id = 1;
        else {
            id = pr.getPersonTreeMap().size() + 1;
            bw.newLine();
        }
        bw.write(new Person(
                id,
                personData[0],
                personData[1],
                personData[2],
                personData[3],
                LocalDate.parse(personData[4], DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        ).toString());
        close();
        System.out.println("Person is written to \"data_list\"");

        check(id, personData);
    }

    // A method check that person is written to file
    private void check(int id, String[] personData) throws IOException {
        PersonReader pr = new PersonReader(path);
        pr.read();

        if (pr.getPersonTreeMap().containsKey(id)) {
            if (pr.getPersonById(id).getId() == id &&
                    pr.getPersonById(id).getLastName().equals(personData[0]) &&
                    pr.getPersonById(id).getFirstName().equals(personData[1]) &&
                    pr.getPersonById(id).getFatherName().equals(personData[2])) {

                this.isWritten = true;
            }
        } else throw new NullPointerException("The person not found by ID");

        System.out.println("Person checked with: " + this.isWritten);
    }

    public void close() throws IOException {
        bw.close();
    }

    public boolean isWritten() {
        return isWritten;
    }
}
