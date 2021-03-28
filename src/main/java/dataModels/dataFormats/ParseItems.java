package dataModels.dataFormats;


import dataModels.models.Product;
import validations.ioExceptions.InvalidTypeException;

/**
 * Klassens metode parseItem() tar imot en text fil som string etter at denne skannet
 * og drivr med å endre hver linje av texten til objekter av type Components eller ConfigurationItems
 * avhengig av lengden på linjen
 * */
public class ParseItems {
    public static Product parseItem(String str) throws InvalidTypeException {
        String[] inputArray = str.split(""+ItemsFormat.DELIMITER);
        if(inputArray.length != 5){
            throw new InvalidTypeException("Feil Type: Prgrammet støtter ikke din fil på grunn av ulike antall " +
                    "attributter enn det programmet forventer");
        }
        int id = Integer.parseInt(inputArray[0]);
        String name = inputArray[1];
        String category = inputArray[2];
        String specs = inputArray[3];
        double price = Double.parseDouble(inputArray[4]);

        Product object = new Product();
        object.setProductID(id);
        object.setProductName(name);
        object.setCategory(category);
        object.setSpecification(specs);
        object.setPrice(price);

        return object;
    }
}