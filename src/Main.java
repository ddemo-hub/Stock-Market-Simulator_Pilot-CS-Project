import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Scene scene = new Scene(new Group());
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/stock.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1050 , 700));
        primaryStage.show();
        primaryStage.setResizable(false);



    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.Load();
        user.Save();
        launch(args);

    }


}
