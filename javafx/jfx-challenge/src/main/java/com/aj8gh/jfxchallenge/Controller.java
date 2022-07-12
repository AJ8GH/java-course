package com.aj8gh.jfxchallenge;

import com.aj8gh.jfxchallenge.domain.Contact;
import com.aj8gh.jfxchallenge.domain.ContactDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableView<Contact> contactsTable;
    private ContactDao contactData;

    public void initialize() {
        contactData = ContactDao.instance();
        contactData.loadContacts();
        contactsTable.setItems(contactData.getContacts());
    }

    @FXML
    public void showAddContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contact-dialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ContactController controller = fxmlLoader.getController();
            Contact contact = controller.createNewContact();
            contactData.add(contact);
            contactData.saveContacts();
        }
    }
}
