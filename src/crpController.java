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

public class crpController implements Initializable {
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
    private TableView<CryptoCurrency> crTable;

    @FXML
    private TableColumn<CryptoCurrency, String> crName;

    @FXML
    private TableColumn<CryptoCurrency, String> crValue;

    @FXML
    private TableColumn<CryptoCurrency, String> crLast;

    @FXML
    private TableColumn<CryptoCurrency, String> crUsable;

    @FXML
    private TableColumn<CryptoCurrency, String> crTotal;

    @FXML
    private TableColumn<CryptoCurrency, String> crVolume;

    @FXML
    private TableColumn<CryptoCurrency, String> crChange;

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
    private Label balance;

    @FXML
    private Label lblBalance;

    ObservableList<String> change = FXCollections.observableArrayList();
    ObservableList<String> volume = FXCollections.observableArrayList();
    ObservableList<String> name = FXCollections.observableArrayList();
    ObservableList<String> total = FXCollections.observableArrayList();
    ObservableList<String> usable = FXCollections.observableArrayList();
    ObservableList<String> last = FXCollections.observableArrayList();
    ObservableList<String> values = FXCollections.observableArrayList();
    int INDEX = 0;





    @FXML
    void calculateBuy(ActionEvent event) {

    }

    @FXML
    void calculateSell(ActionEvent event) {

    }



    @FXML
    void updateTable(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        crName.setCellValueFactory(new PropertyValueFactory<>("name"));
        crValue.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        crLast.setCellValueFactory(new PropertyValueFactory<>("last"));
        crUsable.setCellValueFactory(new PropertyValueFactory<>("high"));
        crTotal.setCellValueFactory(new PropertyValueFactory<>("low"));
        crVolume.setCellValueFactory(new PropertyValueFactory<>("volumeLot"));
        crChange.setCellValueFactory(new PropertyValueFactory<>("percentageChange"));

        try {
            crTable.setItems(getCrypto());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        crTable.getColumns().addAll();
        dropdown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\crypto.txt");
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
                valueShow.setText(last.get(INDEX));


            }
        });



    }
    public ObservableList<CryptoCurrency> getCrypto() throws IOException, ClassNotFoundException {
        ObservableList<CryptoCurrency> coins = FXCollections.observableArrayList();
        File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\crypto.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] details = sc.nextLine().split(",");
            volume.add(details[6]);
            change.add(details[5]);
            name.add(details[0]);
            last.add(details[1]);
            values.add(details[2]);
            usable.add(details[3]);
            total.add(details[4]);



            coins.add(new CryptoCurrency(details[0], details[2], details[1],"", details[3], details[4],"", details[6], details[5],"", ""));
            dropdown.getItems().add(details[0]);

        }

        return coins;
    }


    public void buyCrypto(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
           CryptoCurrency currentCry = new CryptoCurrency(name.get(INDEX), last.get(INDEX), values.get(INDEX), "", usable.get(INDEX), total.get(INDEX), "", volume.get(INDEX), change.get(INDEX), "", "");
            String name = dropdown.getValue();
            lblBuy.setText("");
            System.out.println(dropdown.getValue());
            String amountBought = txtBuy.getText();
            BigDecimal amount = new BigDecimal(amountBought);
            User user = new User();
            user.Load();
            lblBuy.setText(user.buyCrypto(currentCry, amount));
            lblBalance.setText(user.getBalance().toString());
            user.Save();



        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sellCrypto(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            CryptoCurrency currentCry = new CryptoCurrency(name.get(INDEX), last.get(INDEX), values.get(INDEX), "", usable.get(INDEX), total.get(INDEX), "", volume.get(INDEX), change.get(INDEX), "", "");
            String name = dropdown.getValue();
            System.out.println(dropdown.getValue());
            String amountBought = txtSell.getText();
            BigDecimal amount = new BigDecimal(amountBought);
            User user = new User();
            user.Load();
            lblSell.setText(user.sellCrypto(currentCry, amount));
            lblBalance.setText(user.getBalance().toString());


        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    //HANDLERS
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
}
