package dataModels.dataFormats;

import dataModels.models.Product;
import validations.ioExceptions.InvalidTypeException;

import java.util.ArrayList;

/**
 * All Data som blir skrevet til csv fil
 * går gjennom denne klassen for å få sin text format.
 * to av metodene er generiske pga. vi printer ut to Typer til csv-fil
 * Components og ConfigurationItems.
 */

public class ItemsFormat {
    public static final char DELIMITER = ',';

    public static <T> String objectFormat(T object) throws InvalidTypeException {
        if(object instanceof Product){
            return ((Product) object).csvFormat(DELIMITER);
        }
        throw new InvalidTypeException("Programmet støtter ikke denne objekt typen!");

    }

    public static <T> String itemsText(ArrayList<T> items) throws InvalidTypeException {
        StringBuilder itemText = new StringBuilder();
        for( T item: items){
            itemText.append(objectFormat(item));
            itemText.append("\n");
        }
        return itemText.toString();
    }
}