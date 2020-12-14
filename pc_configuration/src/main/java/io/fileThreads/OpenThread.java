package io.fileThreads;

import io.Validation;
import io.open.OpenAbstract;
import io.open.OpenBinBehavior;
import io.open.OpenCsvBehavior;
import javafx.concurrent.Task;
import validations.ioExceptions.FileDontExistsException;
import validations.ioExceptions.InvalidExtensionException;

import java.io.File;
import java.util.ArrayList;

public class OpenThread<T> extends Task<ArrayList<T>> {
    private String path;
    public OpenThread(String path){
        this.path = path;
    }

    private OpenAbstract openBehaviorFactory() throws FileDontExistsException, InvalidExtensionException{
        Validation validpath = new Validation(this.path);
        validpath.pathValidation();
        String extension = this.path.substring(path.lastIndexOf("."));
        switch (extension){
            case ".csv":
                return new OpenCsvBehavior();
            case ".bin":
                return new OpenBinBehavior();
            default:
                throw new InvalidExtensionException("This file type is not supported!\nrequired types: .csv & .bin");
        }
    }

    @Override
    public ArrayList<T> call() throws FileDontExistsException, InvalidExtensionException {
        try { Thread.sleep(2000); }
        catch (InterruptedException ignored){}

        return openBehaviorFactory().read(new File(path));
    }
}
