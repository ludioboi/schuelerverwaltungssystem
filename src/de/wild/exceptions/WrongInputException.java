package de.wild.exceptions;

import de.wild.utils.Logger;

public class WrongInputException extends Exception {

    public WrongInputException(String msg, String usage){
        super(msg + " Please use: " + Logger.Colors.YELLOW + usage);
    }

    public WrongInputException(String usage){
        super("Please use: " + Logger.Colors.YELLOW + usage);
    }

}
