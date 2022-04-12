import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class profileController implements Initializable {

        @FXML
        private Button stock;

        @FXML
        private Button foreign;

        @FXML
        private Button crypto;

        @FXML
        private Button profile;

        @FXML
        private Button stock1;

        @FXML
        private Label lbl, balance;


    @FXML
    public void cryptoHandler(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("fxmlFiles/crp.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }


    public void stockHandler(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("fxmlFiles/stock.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    public void metalHandler(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("fxmlFiles/metal.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    public void foreignHandler(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("fxmlFiles/foreign.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    public void profileHandler(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("fxmlFiles/profile.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            User user = new User();
            user.Load();
            balance.setText(user.getBalance().toString());
            String str = "";
            for (CompanyStock stock: user.ownedStocks) {
                str = str + stock.getName() + ", " + stock.getAmount() + "\n";
            }
            for (ForeignCurrency curr: user.ownedCurrency) {
                str = str + curr.getName() + ", " + curr.getAmount() + "\n";
            }
            for (Metal met: user.ownedMetals) {
                str = str + met.getName() + ", " + met.getAmount() + "\n";
            }
            for (CryptoCurrency cry: user.ownedCrypto) {
                str = str + cry.getName() + ", " + cry.getAmount() + "\n";
            }
            lbl.setText(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


