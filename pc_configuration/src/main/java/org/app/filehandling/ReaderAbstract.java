package org.app.filehandling;

import org.app.validations.customExceptions.InvalidDataException;
import javafx.concurrent.Task;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ReaderAbstract<T> extends Task<ArrayList<T>> {
    protected abstract ArrayList<T> read(String filePath) throws IOException, InvalidDataException;

}