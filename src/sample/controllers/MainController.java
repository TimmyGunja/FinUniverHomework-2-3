package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import sample.Main;

public class MainController {
    // Контроллер для главного меню приложения
    @FXML
    private Button fileButton;

    @FXML
    private Button editButton;

    @FXML
    private Button helpButton;

    public MainController(){  }

//    @FXML
//    public void initialize(){
//
//    }

    @FXML
    private MenuItem mainFileCloseButton;

    @FXML
    private MenuItem mainEditDeleteButton;

    @FXML
    private MenuItem mainHelpAboutButton;

    public void mainFileCloseButton(ActionEvent actionEvent) {
        System.out.println("Меню - закрыть приложение");
    }

    public void mainEditDeleteButton(ActionEvent actionEvent) {
        System.out.println("Меню - далить запись");
    }

    public void mainHelpAboutButton(ActionEvent actionEvent) {
        System.out.println("Меню - о приложении");
    }

}
