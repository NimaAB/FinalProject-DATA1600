package org.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.data.dataCollection.CategoryCollection;
import org.app.data.models.Category;
import org.app.validation.Alerts;
import org.app.validation.Validator;
import org.app.validation.customExceptions.EmptyFieldException;
import org.app.validation.customExceptions.InvalidTextInputException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoryRegisterController implements Initializable {

    @FXML private AnchorPane parentPane;
    @FXML private TextField parentCategoryTextField;
    @FXML private TextField childCategoryTextField;
    @FXML private ListView<String> childCategoryListview;
    private final ObservableList<String> subCategories = FXCollections.observableArrayList();
    private final CategoryCollection CATEGORY_COLLECTION = CategoryCollection.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subCategories.addListener((ListChangeListener<String>) change ->
                childCategoryListview.setItems(subCategories));
    }

    @FXML
    void addNewCategory() {
        try {
            String categoryName = Validator.validateCategory(parentCategoryTextField.getText());

            Category newCategory = new Category(categoryName);
            newCategory.addAll(new ArrayList<>(subCategories));
            CATEGORY_COLLECTION.addCategory(newCategory);

            parentCategoryTextField.setText("");
            childCategoryTextField.setText("");
            subCategories.clear();

            Alerts.success("Kategori opprettet!");
            avbryt();
        } catch (Exception e) {
            Alerts.warning(e.getMessage());
        }
    }

    @FXML
    void addNewSubCategory() {
        try {
            String category = Validator.validateCategory(parentCategoryTextField.getText());
            String subCategoryName = Validator.validateCategory(childCategoryTextField.getText());

            for(Category c: CategoryCollection.CATEGORIES){
                if(c.getName().equals(category)){
                    c.addSubCategory(subCategoryName);
                    break;
                }
            }
            subCategories.add(subCategoryName);
            childCategoryTextField.setText("");
        } catch (EmptyFieldException | InvalidTextInputException e) {
            Alerts.warning(e.getMessage());
        }
    }

    @FXML
    void avbryt() {
        Stage stage = (Stage) parentPane.getScene().getWindow();
        stage.close();
    }
}
