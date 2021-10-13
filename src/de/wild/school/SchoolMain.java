package de.wild.school;

import de.wild.exceptions.PersonNotFoundException;
import de.wild.exceptions.WrongInputException;
import de.wild.utils.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static de.wild.utils.Logger.Colors;

public class SchoolMain {

    static ArrayList<SchoolClass> classes = new ArrayList<>();
    static Teacher teacher1 = new Teacher("Thomas", "Müller", "Oldenburg Spielstraße 1", "01234/809010", new Date());
    static SchoolClass one = new SchoolClass("A", 1, "First class of the level 1");
    static Teacher teacher2 = new Teacher("Roman", "Günther", "Peter", "Oldenburg Spielstraße 2", "01234/809011", new Date());
    static SchoolClass two = new SchoolClass("B", 1, "Second class of the level 1");
    static Teacher teacher3 = new Teacher("Hans", "Rosenkraut", "Koch", "Oldenburg Spielstraße 3", "01234/809012", new Date());
    public static Logger logger;

    public static void main(String[] z) {
        logger = new Logger(false);
        teacher1.setSchoolClass(one);
        teacher2.setSchoolClass(two);
        classes.add(one);
        classes.add(two);
        initStudents();
        logger.setTimeColor(
                Colors.YELLOW_BRIGHT +
                        Colors.BLACK_BACKGROUND);


        logger.log("Here are some commands: ");
        logger.log(
                Colors.YELLOW + "classes " +
                        Colors.YELLOW_BRIGHT + "<add | remove | get | edit> <classLevel + className " +
                        Colors.WHITE + "(e.g.: 1A)" +
                        Colors.YELLOW_BRIGHT + "> " +
                        Colors.YELLOW_BOLD_BRIGHT + "<args...>");
        logger.log(
                Colors.YELLOW + "teachers " +
                        Colors.YELLOW_BRIGHT + "<add | remove | get | edit> <teacherID " +
                        Colors.WHITE + "(e.g.: 0)" +
                        Colors.YELLOW_BRIGHT + "> " +
                        Colors.YELLOW_BOLD_BRIGHT + "<args...>");
        logger.log(
                Colors.YELLOW + "students " +
                        Colors.YELLOW_BRIGHT + "<add | remove | get | edit> <studentID " +
                        Colors.WHITE + "(e.g.: 0)" +
                        Colors.YELLOW_BRIGHT + "> " +
                        Colors.YELLOW_BOLD_BRIGHT + "<args...>");
        logger.log("");

        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] args = input.split(" ");

            try {
                myFunction(args);
            } catch (PersonNotFoundException | WrongInputException e) {
                logger.error(e.getMessage());
            }

            System.out.print("-> ");
        }
    }

    static @Nullable
    SchoolClass getSchoolClass(String ID) {
        for (SchoolClass c : classes) {
            if ((c.getClassLevel() + c.getClassName()).equals(ID)) {
                return c;
            }
        }
        return null;
    }

    static void initStudents() {
        Student a = new Student("Tom", "Meyer", "Oldenburg Spielstraße 6a", "9243/4385435", new Date());
        a.setSchoolClass(one);
        a = new Student("Robert", "Podelski", "Sanchez", "Bremener Geh-Weg 69b", "8347/2394943", new Date());
        a.setSchoolClass(one);
        a = new Student("Heinz", "Geier", "Oldenburg Bubenweg 18c", "4565/328479", new Date());
        a.setSchoolClass(two);
        a = new Student("Hans", "Müller", "Rodrigez", "Bayrischer Jodelpfad 51", "3453/3434294", new Date());
        a.setSchoolClass(two);
    }

    static void printClasses() {
        logger.log("Class LEVEL | Class NAME | Class TEACHER | Class STUDENTS | Class DESCRIPTION");
        for (SchoolClass c : classes) {
            logger.log("     " + c.getClassLevel() + "      |     " + c.getClassName() + "      |      " + (c.getClassTeacher() != null ? c.getClassTeacher().getID() : Colors.RED + "NONE" + Colors.RESET) + "        |       " + c.getStudents().size() + "        |     " + c.getDesc());
        }
    }

    public static void printStudent(Student s) {
        logger.log("[ID " +
                Logger.Colors.YELLOW + s.getID() +
                Colors.RESET + "]" +
                Colors.RESET + " Name: " +
                Colors.YELLOW + s.getFullName() +
                Colors.RESET + " | Class: " + (s.getSchoolClass() != null ?
                Colors.YELLOW + s.getSchoolClass() :
                Colors.RED + "NONE") +
                Colors.RESET + " | Address: " +
                Colors.YELLOW + s.getAddress() +
                Colors.RESET + " | Telephone: " +
                Colors.YELLOW + s.getTel());
    }

    public static void printStudents() {
        logger.log("Every Student");
        for (Student s : Student.getStudents()) {
            printStudent(s);
        }
    }


    public static void printStudents(SchoolClass schoolClass) {
        logger.log("Students for class: " +
                Colors.YELLOW + schoolClass);
        for (Student s : schoolClass.getStudents()) {
            printStudent(s);
        }
    }

    public static void myFunction(String[] args) throws PersonNotFoundException, WrongInputException {
        switch (args[0].toLowerCase()) {

            case "teachers":
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("add")) {
                        if (args.length >= 6) {
                            Teacher t = new Teacher(args[2], args[3], args[4], args[5], new Date());
                            logger.info("Successfully " +
                                    Colors.GREEN_BRIGHT + "added" +
                                    Colors.RESET + " Teacher with ID " +
                                    Colors.YELLOW + t.getID());
                        }
                    } else if (args[1].equalsIgnoreCase("remove")) {
                        Teacher t = Teacher.getTeacher(Integer.parseInt(args[2]));

                        logger.info("Successfully " +
                                Colors.RED + "removed" +
                                Colors.RESET + " teacher with ID " + t.getID());
                        t.removeTeacher();
                    } else if (args[1].equalsIgnoreCase("get")) {
                        if (args.length == 3) {
                            Teacher t = Teacher.getTeacher(Integer.parseInt(args[2]));
                            logger.log("[ID " +
                                    Colors.YELLOW + t.getID() +
                                    Colors.RESET + "] Name: " +
                                    Colors.YELLOW + t.getFullName() +
                                    Colors.RESET + " | Class: " + (t.getSchoolClass() != null ?
                                    Colors.YELLOW + t.getSchoolClass().getClassLevel() + t.getSchoolClass().getClassName() :
                                    Colors.RED + "NONE") +
                                    Colors.RESET + " | Address: " +
                                    Colors.YELLOW + t.getAddress() +
                                    Colors.RESET + " | Telephone: " +
                                    Colors.YELLOW + t.getTel());
                        } else {
                            throw new WrongInputException("teachers get <teacherID>");
                        }
                    } else if (args[1].equalsIgnoreCase("edit")) {
                        if (args.length >= 5) {
                            Teacher s = Teacher.getTeacher(Integer.parseInt(args[2]));
                            if (args[3].equalsIgnoreCase("firstName")) {
                                logger.info("Successfully changed teacher [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "firstName " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getFirstname() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setFirstname(args[4]);
                            } else if (args[3].equalsIgnoreCase("middlename")) {
                                logger.info("Successfully changed teacher [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "MIDDLENAME " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getMidname() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setMidname(args[4]);
                            } else if (args[3].equalsIgnoreCase("lastname")) {
                                logger.info("Successfully changed teacher [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "LASTNAME " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getLastname() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setLastname(args[4]);
                            } else if (args[3].equalsIgnoreCase("fullname")) {
                                if (args.length == 7) {
                                    String oldName = s.getFullName();
                                    s.setFirstname(args[4]);
                                    if (!args[5].isBlank()) {
                                        s.setMidname(args[5]);
                                    } else s.setMidname("");
                                    s.setLastname(args[6]);
                                    logger.info("Successfully changed teacher [ID " +
                                            Colors.YELLOW + s.getID() +
                                            Colors.RESET + "] " +
                                            Colors.PURPLE_BRIGHT + "FULLNAME " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + oldName +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + s.getFullName());
                                } else {
                                    logger.error("Please use: " +
                                            Colors.YELLOW + "teachers edit <studentID> fullName <firstName> <midName> <lastName> <newValue>");
                                }
                            } else if (args[3].equalsIgnoreCase("tel")) {
                                logger.info("Successfully changed teacher [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "TELEPHONE " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getTel() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setTel(args[4]);
                            } else if (args[3].equalsIgnoreCase("address")) {
                                logger.info("Successfully changed teacher [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "ADDRESS " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getAddress() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setAddress(args[4]);
                            } else if (args[3].equalsIgnoreCase("class")) {
                                SchoolClass sc = null;
                                for (SchoolClass c : classes) {
                                    if ((c.getClassLevel() + c.getClassName()).equals(args[4])) {
                                        sc = c;
                                        break;
                                    }
                                }
                                if (sc != null) {
                                    logger.info("Successfully changed teacher [ID " +
                                            Colors.YELLOW + s.getID() +
                                            Colors.RESET + "] " +
                                            Colors.PURPLE_BRIGHT + "CLASS " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + s.getSchoolClass() +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + args[4]);
                                    s.setSchoolClass(sc);
                                } else {
                                    logger.error("Could not find class " +
                                            Colors.YELLOW + args[4]);
                                }
                            } else {
                                throw new WrongInputException("teachers edit <teacherID> <firstName | middleName | lastName | fullName | tel | address | class> <newValue>");
                            }
                        } else {
                            throw new WrongInputException("teachers edit <teacherID> <firstName | middleName | lastName | fullName | tel | address | class> <newValue>");
                        }
                    }
                } else {
                    for (Teacher t : Teacher.getTeachers()) {
                        logger.log("[ID " +
                                Colors.YELLOW + t.getID() +
                                Colors.RESET + "] Name: " +
                                Colors.YELLOW + t.getFullName() +
                                Colors.RESET + " | Class: " + (t.getSchoolClass() != null ?
                                Colors.YELLOW + t.getSchoolClass().getClassLevel() + t.getSchoolClass().getClassName() :
                                Colors.RED + "NONE") +
                                Colors.RESET + " | Address: " +
                                Colors.YELLOW + t.getAddress() +
                                Colors.RESET + " | Telephone: " +
                                Colors.YELLOW + t.getTel());
                    }
                }
                break;

            case "students":
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("add")) {
                        if (args.length > 7) {
                            StringBuilder address = new StringBuilder();
                            for (int i = 6; i < args.length; i++) {
                                address.append(" ").append(args[i]);
                            }
                            Student s = new Student(args[2], args[3], args[4], address.toString(), args[5], new Date());
                            logger.info("Succesfully " +
                                    Colors.GREEN_BRIGHT + "added" +
                                    Colors.RESET + " Student with ID " +
                                    Colors.YELLOW + s.getID());
                        } else {
                            throw new WrongInputException("students add <firstName> <middlename> <lastname> <telephone> <address" +
                                    Colors.YELLOW_BRIGHT + "..." +
                                    Colors.YELLOW + ">");
                        }
                    } else if (args[1].equalsIgnoreCase("remove")) {
                        if (args.length == 3) {
                            Student s = Student.getStudent(Integer.parseInt(args[2]));
                            logger.info("Succesfully " +
                                    Colors.RED + "removed" +
                                    Colors.RESET + " Student with ID " +
                                    Colors.YELLOW + s.getID());
                            s.removeStudent();
                        } else {
                            throw new WrongInputException("students remove <studentID>");
                        }


                    } else if (args[1].equalsIgnoreCase("get")) {
                        if (args.length == 3) {
                            Student s = Student.getStudent(Integer.parseInt(args[2]));
                            printStudent(s);
                        } else {
                            throw new WrongInputException("students get <studentID>");
                        }
                    } else if (args[1].equalsIgnoreCase("edit")) {
                        if (args.length >= 5) {
                            Student s = Student.getStudent(Integer.parseInt(args[2]));
                            if (args[3].equalsIgnoreCase("firstName")) {
                                logger.info("Successfully changed student [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "FIRSTNAME " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getFirstname() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setFirstname(args[4]);
                            } else if (args[3].equalsIgnoreCase("middlename")) {
                                logger.info("Successfully changed student [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "MIDDLENAME " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getMidname() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setMidname(args[4]);
                            } else if (args[3].equalsIgnoreCase("lastname")) {
                                logger.info("Successfully changed student [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "LASTNAME " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getLastname() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setLastname(args[4]);
                            } else if (args[3].equalsIgnoreCase("fullname")) {
                                if (args.length == 7) {
                                    String oldName = s.getFullName();
                                    s.setFirstname(args[4]);
                                    if (!args[5].isBlank()) {
                                        s.setMidname(args[5]);
                                    } else s.setMidname("");
                                    s.setLastname(args[6]);
                                    logger.info("Successfully changed student [ID " +
                                            Colors.YELLOW + s.getID() +
                                            Colors.RESET + "] " +
                                            Colors.PURPLE_BRIGHT + "FULLNAME " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + oldName +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + s.getFullName());
                                } else {
                                    throw new WrongInputException("students edit <studentID> fullName <firstName> <midName> <lastName> <newValue>");
                                }
                            } else if (args[3].equalsIgnoreCase("tel")) {
                                logger.info("Successfully changed student [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "TELEPHONE " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getTel() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setTel(args[4]);
                            } else if (args[3].equalsIgnoreCase("address")) {
                                logger.info("Successfully changed student [ID " +
                                        Colors.YELLOW + s.getID() +
                                        Colors.RESET + "] " +
                                        Colors.PURPLE_BRIGHT + "ADDRESS " +
                                        Colors.RESET + "from " +
                                        Colors.YELLOW + s.getAddress() +
                                        Colors.RESET + " to " +
                                        Colors.YELLOW_BRIGHT + args[4]);
                                s.setAddress(args[4]);
                            } else if (args[3].equalsIgnoreCase("class")) {
                                SchoolClass sc = null;
                                for (SchoolClass c : classes) {
                                    if ((c.getClassLevel() + c.getClassName()).equals(args[4])) {
                                        sc = c;
                                        break;
                                    }
                                }
                                if (sc != null) {
                                    logger.info("Successfully changed student [ID " +
                                            Colors.YELLOW + s.getID() +
                                            Colors.RESET + "] " +
                                            Colors.PURPLE_BRIGHT + "CLASS " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + s.getSchoolClass() +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + args[4]);
                                    s.setSchoolClass(sc);
                                } else {
                                    logger.error("Could not find class " +
                                            Colors.YELLOW + args[4]);
                                }
                            } else {
                                throw new WrongInputException("students edit <studentID> <firstName | middleName | lastName | fullName | tel | address | class> <newValue>");
                            }
                        } else {
                            throw new WrongInputException("students edit <studentID> <firstName | middleName | lastName | fullName | tel | address | class> <newValue>");
                        }
                    }
                } else {
                    printStudents();
                }
                break;

            case "classes":
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("remove")) {
                        if (args.length == 3) {
                            SchoolClass s = getSchoolClass(args[2]);
                            if (s != null) {
                                logger.info("Removed class: " + s.getClassLevel() + s.getClassName());
                                s.setClassTeacher(null);
                                s.clearStudents();
                                classes.remove(s);
                            } else logger.error("Could not find: " +
                                    Colors.YELLOW + args[2]);
                        } else {
                            throw new WrongInputException("classes remove <classLevel + className>");
                        }
                    } else if (args[1].equalsIgnoreCase("get")) {
                        if (args.length == 3) {
                            SchoolClass c = getSchoolClass(args[2]);
                            if (c != null) {
                                logger.log("Class LEVEL | Class NAME | Class TEACHER | Class STUDENTS | Class DESCRIPTION");
                                logger.log("     " + c.getClassLevel() + "      |     " + c.getClassName() + "      |      " + c.getClassTeacher().getID() + "        |       " + c.getStudents().size() + "        |     " + c.getDesc());
                                printStudents(c);
                            } else logger.error("Could not find: " +
                                    Colors.YELLOW + args[2]);
                        } else {
                            throw new WrongInputException("classes get <classLevel + className>");
                        }
                    } else if (args[1].equalsIgnoreCase("add")) {
                        if (args.length == 5) {
                            Teacher t = Teacher.getTeacher(Integer.parseInt(args[4]));
                            classes.add(new SchoolClass(args[3], Integer.parseInt(args[2]), "Created by Console User", t));
                            logger.info("Successful added class " +
                                    Colors.YELLOW + args[2] + args[3]);
                        } else {
                            throw new WrongInputException("classes add <classLevel> <className> <classTeacherID>");
                        }
                    } else if (args[1].equalsIgnoreCase("edit")) {
                        if (args.length > 4) {
                            SchoolClass schoolClass = null;
                            for (SchoolClass c : classes) {
                                if ((c.getClassLevel() + c.getClassName()).equals(args[2])) {
                                    schoolClass = c;
                                    break;
                                }
                            }

                            if (schoolClass != null) {
                                if (args[3].equalsIgnoreCase("level")) {
                                    logger.info("Successfully changed class " +
                                            Colors.PURPLE_BRIGHT + "LEVEL " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + schoolClass.getClassLevel() +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + Integer.parseInt(args[4]));
                                    schoolClass.setClassLevel(Integer.parseInt(args[4]));
                                } else if (args[3].equalsIgnoreCase("name")) {
                                    logger.info("Successfully changed class " +
                                            Colors.PURPLE_BRIGHT + "NAME " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + schoolClass.getClassName() +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + args[4]);
                                    schoolClass.setClassName(args[4]);
                                } else if (args[3].equalsIgnoreCase("desc")) {
                                    logger.info("Successfully changed class " +
                                            Colors.PURPLE_BRIGHT + "DESCRIPTION " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + schoolClass.getDesc() +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + args[4]);
                                    schoolClass.setDesc(args[4]);
                                } else if (args[3].equalsIgnoreCase("teacher")) {
                                    Teacher t = Teacher.getTeacher(Integer.parseInt(args[4]));
                                    logger.info("Successfully changed class " +
                                            Colors.PURPLE_BRIGHT + "TEACHER " +
                                            Colors.RESET + "from " +
                                            Colors.YELLOW + (schoolClass.getClassTeacher() != null ? schoolClass.getClassTeacher() : Colors.RED + "NONE") +
                                            Colors.RESET + " to " +
                                            Colors.YELLOW_BRIGHT + t);
                                    t.setSchoolClass(schoolClass);

                                } else if (args[3].equals("students")) {
                                    if (args.length == 6) {
                                        if (args[4].equalsIgnoreCase("add")) {
                                            Student s = Student.getStudent(Integer.parseInt(args[5]));
                                            if (s.getSchoolClass() == schoolClass) {
                                                s.setSchoolClass(schoolClass);
                                            } else {
                                                schoolClass.addStudent(s);
                                            }
                                            logger.info("Successfully " +
                                                    Colors.GREEN_BRIGHT + "added" +
                                                    Colors.RESET + " student [ID " +
                                                    Colors.YELLOW + s.getID() +
                                                    Colors.RESET + "] to class");
                                        } else if (args[4].equalsIgnoreCase("remove")) {
                                            Student s = Student.getStudent(Integer.parseInt(args[5]));
                                            schoolClass.removeStudent(s);
                                            if (s.getSchoolClass() == schoolClass) {
                                                s.setSchoolClass(null);
                                            }
                                            logger.info("Successfully " +
                                                    Colors.RED_BRIGHT + "removed" +
                                                    Colors.RESET + " student [ID " +
                                                    Colors.YELLOW + s.getID() +
                                                    Colors.RESET + "] from class");
                                        } else {
                                            throw new WrongInputException("classes edit <classLevel + className> students <add | remove> <studentsID>");
                                        }
                                    } else {
                                        throw new WrongInputException("classes edit <classLevel + className> students <add | remove> <studentsID>");
                                    }
                                }
                            } else {
                                logger.error("Could not find class " +
                                        Colors.YELLOW + args[2]);
                            }
                        } else {
                            throw new WrongInputException("classes edit <classLevel + className> <level | name | desc | teacher | students " +
                                    Colors.YELLOW_BRIGHT + "<add | remove> <studentID>" +
                                    Colors.YELLOW + ">");
                        }
                    }
                } else {
                    logger.log("Every Class");
                    printClasses();
                }
                break;
            case "timestamp":
                if (args.length == 1) {
                    logger.setTimestampEnabled(!logger.getTimestampEnabled());

                } else {
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("on")) {
                            logger.setTimestampEnabled(true);
                        } else if (args[1].equalsIgnoreCase("off")) {
                            logger.setTimestampEnabled(false);
                        } else {
                            logger.setTimestampEnabled(Boolean.parseBoolean(args[1]));
                        }
                    }
                }

                if (logger.getTimestampEnabled()) {
                    logger.info("Timestamp " + Colors.GREEN_BRIGHT + "enabled");
                } else logger.info("Timestamp " + Colors.RED + "disabled");
                break;
        }

    }

}
