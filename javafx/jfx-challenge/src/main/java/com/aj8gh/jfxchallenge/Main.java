package com.aj8gh.jfxchallenge;

import com.aj8gh.jfxchallenge.domain.ContactDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        ContactDao.instance().saveContacts();
    }

    @Override
    public void stop() throws Exception {
        ContactDao.instance().loadContacts();
        super.stop();
    }
}
