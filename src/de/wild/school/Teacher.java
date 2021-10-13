package de.wild.school;

import de.wild.exceptions.PersonNotFoundException;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class Teacher extends Person {

    private SchoolClass schoolClass;

    private static final ArrayList<Teacher> teachers = new ArrayList<>();

    public Teacher(SchoolClass schoolClass) {
        super();
        this.schoolClass = schoolClass;
        teachers.add(this);
    }

    public Teacher(String forename, String lastname, String address, String tel, Date birthdate) {
        super(forename, lastname, address, tel, birthdate);
        teachers.add(this);
    }

    public Teacher(String forename, String midname, String lastname, String address, String tel, Date birthdate) {
        super(forename, midname, lastname, address, tel, birthdate);
        teachers.add(this);
    }

    public void setSchoolClass(SchoolClass schoolClass){
        if (this.schoolClass != null) this.schoolClass.setClassTeacher(null);
        schoolClass.setClassTeacher(this);
        this.schoolClass = schoolClass;
    }

    public SchoolClass getSchoolClass(){
        return schoolClass;
    }

    public int getID(){
        for (int i = 0; i < teachers.size(); i++){
            if (teachers.get(i) == this) return i;
        }
        return -1;
    }



    public static Teacher getTeacher(int id) throws PersonNotFoundException {
        for (Teacher teacher : teachers) {
            if (teacher.getID() == id) return teacher;
        }
        throw new PersonNotFoundException(PersonNotFoundException.PersonType.TEACHER, id);
    }

    public void removeTeacher(){
        if (this.schoolClass != null){
            this.schoolClass.setClassTeacher(null);
        }
        teachers.remove(this);
    }

    public Person getPerson(){
        return this;
    }

    public static ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
