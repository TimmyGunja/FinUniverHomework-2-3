package javaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javaFX.controllers.PersonEditDialogController;
import javaFX.controllers.PersonOverviewController;
import javaFX.models.Person;

import java.io.IOException;
import java.util.Random;

public class Main extends Application {

    private ObservableList<Person> personData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private BorderPane rootLayout;

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public Main(){
        for (int i=0; i < 15;i++){
            Random random = new Random();
            personData.add(new Person("Имя " + i ,"Фамилия " + random.nextInt(30)));
        }
    }

    public static ObservableList<Person> GeneratePersons(){
        final Random random = new Random();
        ObservableList<Person> locData = FXCollections.observableArrayList();

        for (int i=1; i <= 15;i++){
            locData.add(new Person("Имя " + i ,"Фамилия " + random.nextInt(30)));
        }

        return locData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Приложение адресов");

//        personData = Main.GeneratePersons();
//        for (Person element : personData){
//            System.out.println(element.getFirstName() + " " + element.getLastName());
//        }

        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showPersonOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/Main.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);
            PersonOverviewController PController= loader.getController();
            PController.setMainApp(this);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Открывает диалоговое окно для изменения деталей указанного адресата.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте адресата и возвращается значение true.
     *
     * @param person - объект адресата, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
