package org.app.validation.ioExceptions;

import java.io.FileNotFoundException;

public class FileDontExistsException extends FileNotFoundException {
    public FileDontExistsException(String msg){
        super(msg);
    }
}
