import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class frController implements Initializable {
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
    private TableView<ForeignCurrency> foreignTable;

    @FXML
    private TableColumn<ForeignCurrency, String> frName;

    @FXML
    private TableColumn<ForeignCurrency, String> frBuy;

    @FXML
    private TableColumn<ForeignCurrency, String> frSell;

    @FXML
    private TableColumn<ForeignCurrency, String> frChange;

    @FXML
    private TableColumn<ForeignCurrency, String> frTime;

    @FXML
    private ChoiceBox<String> dropdown;

    @FXML
    private TextField txtBuy;

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnSell;

    @FXML
    private TextField txtSell;

    @FXML
    private Label lblValue;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblBuy;

    @FXML
    private Label lblSell;

    @FXML
    private Button buyCalculate;

    @FXML
    private Button sellCalculate;

    @FXML
    private Label valueShow;

    @FXML
    private Label txtBalance;

    @FXML
    private Label lblBalance;

    int INDEX;
    ObservableList<String> sell = FXCollections.observableArrayList();
    ObservableList<String> buy = FXCollections.observableArrayList();
    ObservableList<String> name = FXCollections.observableArrayList();
    ObservableList<String> change = FXCollections.observableArrayList();
    ObservableList<String> time = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        frName.setCellValueFactory(new PropertyValueFactory<>("name"));
        frBuy.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        frSell.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        frChange.setCellValueFactory(new PropertyValueFactory<>("percentageChange"));
        frTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            foreignTable.setItems(getForeign());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        foreignTable.getColumns().addAll();

        dropdown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\currency.txt");
                Scanner sc = null;
                try {
                    sc = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc.hasNextLine()){
                    String[] details = sc.nextLine().split(",");
                }
                INDEX = new_value.intValue();
                valueShow.setText(sell.get(INDEX));




            }
        });

    }
    public ObservableList<ForeignCurrency> getForeign() throws IOException, ClassNotFoundException {
        ObservableList<ForeignCurrency> coins = FXCollections.observableArrayList();
        File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\currency.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] details = sc.nextLine().split(",");
            buy.add(details[1]);
            sell.add(details[2]);
            name.add(details[0]);
            change.add(details[3]);
            time.add(details[4]);


            coins.add(new ForeignCurrency(details[0], "", details[1],details[2], "", "","", details[3], "","", details[4]));
            dropdown.getItems().add(details[0]);

        }

        return coins;
    }
    @FXML
    void buyCurrency(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            ForeignCurrency currentCurrency = new ForeignCurrency(name.get(INDEX), "", buy.get(INDEX), sell.get(INDEX), "", "", "", change.get(INDEX),"", "s", time.get(INDEX));
            String name = dropdown.getValue();
            lblBuy.setText("");
            System.out.println(dropdown.getValue());
            String amountBought = txtBuy.getText();
            BigDecimal amount = new BigDecimal(amountBought);
            User user = new User();
            user.Load();
            lblBuy.setText(user.buyCurrency(currentCurrency, amount));
            lblBalance.setText(user.getBalance().toString());
            user.Save();


        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    public void sellCurrency(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            ForeignCurrency currentCurrency = new ForeignCurrency(name.get(INDEX), "", buy.get(INDEX), sell.get(INDEX), "", "", "", change.get(INDEX), "", "", time.get(INDEX));
            System.out.println(dropdown.getValue());
            String amountBought = txtSell.getText();
            BigDecimal amount = new BigDecimal(amountBought);
            User user = new User();
            user.Load();
            lblSell.setText(user.sellCurrency(currentCurrency, amount));
            lblBalance.setText(user.getBalance().toString());


        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void calculateBuy(ActionEvent event) {

    }

    @FXML
    void calculateSell(ActionEvent event) {

    }


    @FXML
    void updateTable(ActionEvent event) {

    }

    //BUTTON HANDLERS
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
    public void updateTable() throws IOException, ClassNotFoundException {
        updateCurrency();
        foreignTable.getItems().clear();
        foreignTable.getItems().addAll(getForeign());

    }

    private void updateCurrency() {
        WebScrape webScrape = new WebScrape();
        webScrape.setDataCurrency();
        webScrape.writeDataOn();
    }


}
