package de.wild.school;

import de.wild.exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.Date;

public class Student extends Person {

    static ArrayList<Student> students = new ArrayList<>();
    private SchoolClass schoolClass;
    public Student(){
        super();
        students.add(this);
    }

    public Student(String forename, String midname, String lastname, String address, String tel, Date birthdate) {
        super(forename, midname, lastname, address, tel, birthdate);
        students.add(this);
    }

    public Student(String forename, String lastname, String address, String tel, Date birthdate) {
        super(forename, lastname, address, tel, birthdate);
        students.add(this);
    }

    public void setSchoolClass(SchoolClass schoolClass){
        this.schoolClass = schoolClass;
        if (schoolClass != null){
            schoolClass.addStudent(this);
        }

    }

    public SchoolClass getSchoolClass(){
        return this.schoolClass;
    }

    public int getID(){
        for (int i = 0; i < students.size(); i++){
            if (students.get(i) == this) return i;
        }
        return -1;
    }

    public void removeStudent(){
        students.remove(this);
        if (schoolClass != null){
            this.schoolClass.removeStudent(this);
        }
    }

    public void removeFromClass(){
        this.schoolClass.removeStudent(this);
    }

    public void moveToClass(SchoolClass schoolClass){
        this.schoolClass.removeStudent(this);
        this.schoolClass = schoolClass;
    }

    public Teacher getHeadTeacher(){
        return this.schoolClass.getClassTeacher();
    }

    public void addToClass(SchoolClass schoolClass){
        schoolClass.addStudent(this);
    }

    public void removeFromClass(SchoolClass schoolClass){
        schoolClass.removeStudent(this);
    }

    public static Student getStudent(int id) throws PersonNotFoundException {
        for (Student s : students){
            if (s.getID() == id) return s;
        }
        throw new PersonNotFoundException(PersonNotFoundException.PersonType.STUDENT, id);
    }

    public static ArrayList<Student> getStudents(){
        return students;
    }

    public Person getPerson(){
        return this;
    }

}
