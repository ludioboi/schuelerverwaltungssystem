package de.wild.exceptions;

import de.wild.school.Person;
import de.wild.utils.Logger;

public class PersonNotFoundException extends Exception {

    public enum PersonType {
        TEACHER,
        STUDENT,
        PERSON
    }

    public PersonNotFoundException(int id){
        super("Could not find ID " + Logger.Colors.YELLOW + id);
    }

    public PersonNotFoundException(PersonType type, int id){
        super("Could not find " + type.name().toLowerCase() + " ID " + Logger.Colors.YELLOW + id);
    }

}
