package data.obj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person implements Comparable<Person>/*, Externalizable*/ {

    /* The id will be create when this person
     * have been adding to file.
     * The age will be create when be set a birth date.
     */
    private int           id;
    private LocalDate     birthDate;
    private String        lastName, firstName, fatherName, sex;
    private transient int age;

    public Person(int id, String lastName, String firstName, String fatherName,
                    String sex, LocalDate birthDate) {
        this.id         = id;
        this.lastName   = lastName;
        this.firstName  = firstName;
        this.fatherName = fatherName;
        this.sex        = sex;
        this.birthDate  = birthDate;
        this.age        = createAge(birthDate);
    }

    /* This method return age. The method create age like:
       age equals year from now minus year of birth date. */
    private int createAge(LocalDate date) {
        return LocalDate.now().getYear() - date.getYear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = Math.max(id, 0);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // A method doesn't only setting a date of birth, also creating an age.
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        this.age       = createAge(birthDate);
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        // An age is creatable value.
        return "Person{" +
                "id="         + id + ", " +
                "lastName="   + lastName + ", " +
                "firstName="  + firstName + ", " +
                "fatherName=" + fatherName + ", " +
                "sex="        + sex + ", " +
                "birthDate="  + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        if (id < o.getId()) return -1;
        else if (id > o.getId()) return 1;
        return 0;
    }

    /* These methods was for realisation an Externalizable interface.
       I decided that it's not a good thing for data transfer */
    /*@Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeObject(lastName);
        out.writeObject(firstName);
        out.writeObject(fatherName);
        out.writeObject(sex);
        out.writeObject(birthDate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id         = in.readInt();
        lastName   = (String) in.readObject();
        firstName  = (String) in.readObject();
        fatherName = (String) in.readObject();
        sex        = (String) in.readObject();
        birthDate  = (LocalDate) in.readObject();
        age        = createAge(birthDate);
    }*/
}
