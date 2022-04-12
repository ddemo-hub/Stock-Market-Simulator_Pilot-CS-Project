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

import javax.swing.event.ChangeEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class tableController implements Serializable, Initializable  {

    ObservableList<String> sell = FXCollections.observableArrayList();
    ObservableList<String> buy = FXCollections.observableArrayList();
    ObservableList<String> name = FXCollections.observableArrayList();
    ObservableList<String> high = FXCollections.observableArrayList();
    ObservableList<String> low = FXCollections.observableArrayList();
    ObservableList<String> last = FXCollections.observableArrayList();
    ObservableList<String> change = FXCollections.observableArrayList();
    ObservableList<String> VLot = FXCollections.observableArrayList();
    ObservableList<String> VTL = FXCollections.observableArrayList();
    ObservableList<String> time = FXCollections.observableArrayList();
    ObservableList<String> average = FXCollections.observableArrayList();
    int INDEX = 0;

    @FXML
    private TableView<CompanyStock> stockTable;

    @FXML
    private TableColumn<CompanyStock, String> stockColName;

    @FXML
    private TableColumn<CompanyStock, String> stockColLast;

    @FXML
    private TableColumn<CompanyStock, String> stockColBuy;

    @FXML
    private TableColumn<CompanyStock, String> stockColSell;

    @FXML
    private TableColumn<CompanyStock, String> stockColHigh;

    @FXML
    private TableColumn<CompanyStock, String> stockColLow;

    @FXML
    private TableColumn<CompanyStock, String> stockColAve;

    @FXML
    private TableColumn<CompanyStock, String> stockColChange;

    @FXML
    private TableColumn<CompanyStock, String> stockColVLot;

    @FXML
    private TableColumn<CompanyStock, String> stockColVTL;

    @FXML
    private TableColumn<CompanyStock, String> stockColTime;
    @FXML
    TextField txt;
    @FXML
    Label lblSell, lblBuy, buyValue, SellValue;
    @FXML
    ChoiceBox<String> dropdown;

    @FXML
    private TextField txtBuy;

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnSell;

    @FXML
    private TextField txtSell;

    @FXML
    private Label price;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button buyCalculate;

    @FXML
    private Button sellCalculate;

    @FXML
    private Button stock, crypto, foreign, profile;

    @FXML
    private Label txtBalance;

    @FXML
    private Label lblBalance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        stockColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColLast.setCellValueFactory(new PropertyValueFactory<>("last"));
        stockColBuy.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        stockColSell.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        stockColHigh.setCellValueFactory(new PropertyValueFactory<>("high"));
        stockColLow.setCellValueFactory(new PropertyValueFactory<>("low"));
        stockColAve.setCellValueFactory(new PropertyValueFactory<>("average"));
        stockColChange.setCellValueFactory(new PropertyValueFactory<>("percentageChange"));
        stockColVLot.setCellValueFactory(new PropertyValueFactory<>("volumeLot"));
        stockColVTL.setCellValueFactory(new PropertyValueFactory<>("VolumeTL"));
        stockColTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            stockTable.setItems(getCompany());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        stockTable.getColumns().addAll();



        // add a listener
        dropdown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\bist100.txt");
                Scanner sc = null;
                try {
                    sc = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (sc.hasNextLine()){
                    String[] details = sc.nextLine().split(" ");
                }
                INDEX = new_value.intValue();
                buyValue.setText(last.get(INDEX));


            }
        });

    }
    public ObservableList<CompanyStock> getCompany() throws IOException, ClassNotFoundException {
        ObservableList<CompanyStock> companies1 = FXCollections.observableArrayList();
        File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\bist100.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] details = sc.nextLine().split(" ");
            buy.add(details[2]);
            sell.add(details[3]);
            name.add(details[0]);
            last.add(details[1]);
            high.add(details[4]);
            low.add(details[5]);
            average.add(details[6]);
            change.add(details[7]);
            VLot.add(details[8]);
            VTL.add(details[9]);
            time.add(details[10]);

            companies1.add(new CompanyStock(details[0], details[1], details[2],details[3], details[4], details[5],details[6], details[7], details[8],details[9], details[10]));
            dropdown.getItems().add(details[0]);

        }

        return companies1;
    }



    public void updateTable() throws IOException, ClassNotFoundException {
        updateCompany();
        stockTable.getItems().clear();
        stockTable.getItems().addAll(getCompany());

    }

    private void updateCompany() {
        WebScrape webScrape = new WebScrape();
        webScrape.setDataBist();
        webScrape.writeDataOn();
    }

    public void buyStock(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            CompanyStock currentCompany = new CompanyStock(name.get(INDEX), last.get(INDEX), buy.get(INDEX), sell.get(INDEX), high.get(INDEX), low.get(INDEX), average.get(INDEX), change.get(INDEX), VLot.get(INDEX), VTL.get(INDEX), time.get(INDEX));
            String name = dropdown.getValue();
            lblBuy.setText("");
            System.out.println(dropdown.getValue());
            String amountBought = txtBuy.getText();
            BigDecimal amount = new BigDecimal(amountBought);
            User user = new User();
            user.Load();
            lblBuy.setText(user.buyStock(currentCompany, amount));
            lblBalance.setText(user.getBalance().toString());
            user.Save();



        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sellStock(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            CompanyStock currentCompany = new CompanyStock(name.get(INDEX), last.get(INDEX), buy.get(INDEX), sell.get(INDEX), high.get(INDEX), low.get(INDEX), average.get(INDEX), change.get(INDEX), VLot.get(INDEX), VTL.get(INDEX), time.get(INDEX));
            String name = dropdown.getValue();
            System.out.println(dropdown.getValue());
            String amountBought = txtSell.getText();
            BigDecimal amount = new BigDecimal(amountBought);
            User user = new User();
            user.Load();
            lblSell.setText(user.sellStock(currentCompany, amount));
            lblBalance.setText(user.getBalance().toString());


        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }



    public void calculateBuy(ActionEvent event) {
        BigDecimal value = new BigDecimal(txtBuy.getText());
        lblBuy.setText(String.valueOf(value.multiply(new BigDecimal(buyValue.getText()))));

    }
    public void calculateSell(ActionEvent event) {
        BigDecimal value = new BigDecimal(txtBuy.getText());
        lblSell.setText(String.valueOf(value.multiply(new BigDecimal(buyValue.getText()))));

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

}
