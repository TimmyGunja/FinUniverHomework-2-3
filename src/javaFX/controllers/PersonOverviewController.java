package javaFX.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javaFX.Main;
import javaFX.models.Person;
import javaFX.utils.DateUtil;

public class PersonOverviewController{
    @FXML
    private TableView<Person> personTableView;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    public void setPersonTableView(TableView<Person> personTableView) {
        this.personTableView = personTableView;
    }

    // Labels block

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label birthdayLabel;

    // Buttons block

    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    // Reference to the main application.
    private Main main;


    public PersonOverviewController(){  }


    @FXML
    public void initialize(){
        // Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

        // Заполнение таблицы случайными данными
        personTableView.setItems(Main.GeneratePersons());

        // Очистка дополнительной информации об адресате.
        showPersonDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию об адресате.
        personTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main
     */
    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        personTableView.setItems(main.getPersonData());
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person("Имя", "Фамилия");
        boolean okClicked = main.showPersonEditDialog(tempPerson);
        if (okClicked) {
            main.getPersonData().add(tempPerson);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTableView.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Заполняет все текстовые поля, отображая подробности об адресате.
     * Если указанный адресат = null, то все текстовые поля очищаются.
     *
     * @param person — адресат типа Person или null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Заполняем метки информацией из объекта person.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            // birthdayLabel.setText(...);
        } else {
            // Если Person = null, то убираем весь текст.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }



    public void newButton(ActionEvent actionEvent) {
        System.out.println("Создать новую запись");
    }

    public void editButton(ActionEvent actionEvent) {
        System.out.println("Редактировать");
    }

    public void deleteButton(ActionEvent actionEvent) {
        System.out.println("Удалить");
//
//        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = main.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
//            }
//
//        } else {
//            // Ничего не выбрано.
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
    }

}
