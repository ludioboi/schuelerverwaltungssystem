package de.wild.school;

import java.util.ArrayList;

public class SchoolClass {

    private String className, desc;
    private int classLevel;
    private Teacher classTeacher;
    private final ArrayList<Student> students;

    public SchoolClass(String className, int classLevel, String desc, Teacher classTeacher) {
        this.classLevel = classLevel;
        this.className = className;
        this.classTeacher = classTeacher;
        classTeacher.setSchoolClass(this);
        this.desc = desc;
        this.students = new ArrayList<>();
    }

    public SchoolClass(String className, int classLevel, String desc) {
        this.className = className;
        this.desc = desc;
        this.classLevel = classLevel;
        this.students = new ArrayList<>();
    }


    public void addStudent(Student s) {
        if (s.getSchoolClass() == null) {
            s.setSchoolClass(this);
        } else this.students.add(s);

    }

    public void removeStudent(Student s) {
        this.students.remove(s);
        if (s.getSchoolClass() == this) {
            s.setSchoolClass(null);
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int students() {
        return students.size();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public Teacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }

    public void clearStudents() {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getSchoolClass() == this) s.setSchoolClass(null);
            System.out.println(s.getID());
        }
        students.clear();
    }

    @Override
    public String toString() {
        return classLevel + className;
    }
}
