import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.util.*;

public class AutoParkView extends Pane {

    AutoPark model;
    private ListView<String> invList, cartList, popList;
    private AutoParkButtonPane buttonPane;
    private TextField numSalesField, revField, avgSaleField;

    private Label cartLabel;

    public ListView<String> getInvList() {return invList;}
    public ListView<String> getCartList() {return cartList;}
    public ListView<String> getPopList() {return popList;}
    public AutoParkButtonPane getButtonPane() {return buttonPane;}
    public TextField getNumSalesField() {return numSalesField;}
    public TextField getRevField() {return revField;}
    public TextField getAvgSaleField() {return avgSaleField;}


    public AutoParkView(AutoPark initModel)
    {
        model = initModel;
        Label invLabel = new Label("Park Inventory:");
        invLabel.relocate(30, 10);

        cartLabel = new Label("Current Cart ($0.0):");
        cartLabel.relocate(290, 10);

        Label popLabel = new Label("Most Popular Items:");
        popLabel.relocate(550, 120);

        Label parkSumLabel = new Label("Park Summary:");
        parkSumLabel.relocate(550, 5);

        Label numSalesLabel = new Label("# Sales:");
        numSalesLabel.relocate(560, 30);

        numSalesField = new TextField();
        numSalesField.relocate(630, 30);
        numSalesField.setPrefSize(150,15);

        Label revLabel = new Label("Revenue:");
        revLabel.relocate(560, 60);

        revField = new TextField();
        revField.relocate(630, 60);
        revField.setPrefSize(150,15);

        Label avgSaleLabel = new Label("$ / Sale:");
        avgSaleLabel.relocate(560, 90);

        avgSaleField = new TextField();
        avgSaleField.relocate(630, 90);
        avgSaleField.setPrefSize(150,15);

        invList = new ListView<String>();
        invList.relocate(30, 30);
        invList.setPrefSize(250,260);

        cartList = new ListView<String>();
        cartList.relocate(290, 30);
        cartList.setPrefSize(250,260);

        popList = new ListView<String>();
        popList.relocate(550, 140);
        popList.setPrefSize(225,150);

        buttonPane = new AutoParkButtonPane();
        buttonPane.relocate(115,300);

        getChildren().addAll(invLabel, cartLabel, popLabel, parkSumLabel, numSalesLabel, revLabel, avgSaleLabel,
                invList, cartList, popList, buttonPane,
                numSalesField, revField, avgSaleField);
        setPrefSize(800, 350);
    }

    public void update()
    {
        List<String> inventory = new ArrayList<String>();
        for (int i=0; i < model.getTotalItem(); i++)
        { // Sets inventory list
            if (model.getItems()[i].getInvQuantity() > 0)
            { // Only adds Items that are in stock (have invQuantity above 0)
                inventory.add(model.getItems()[i].toString());
            }
        }
        invList.setItems(FXCollections.observableArrayList(inventory));

        double currentCart = model.getCurrentCart();
        List<String> cart = model.getObservableCart(); // Gets the cart in the form of a list
        if (cart.isEmpty())
        { // Disables the complete sale button if the cart is empty
            buttonPane.getCompleteSaleButton().setDisable(true);
        }
        cartLabel.setText("Current Cart ($" + currentCart + "):"); // Displays the cost of all the Items in the cart
        cartList.setItems(FXCollections.observableArrayList(cart));

        numSalesField.setText(String.valueOf(model.getNumSales()));
        revField.setText(String.valueOf(model.getRevenue()));
        if (model.getNumSales() > 0)
        {
            avgSaleField.setText(String.valueOf(model.getRevenue()/model.getNumSales()));
        }
        else
        { // Displays N/A to avoid divide by 0 error
            avgSaleField.setText("N/A");
        }
    }

    public void updatePopItems()
    { // Updates the 3 most popular Items
        List<String> mostPopItems = new ArrayList<String>();
        for (Item i : model.getXPopularItems(3))
        { // Adds the 3 most popular Items to the list
            mostPopItems.add(i.toString());
        }
        popList.setItems(FXCollections.observableArrayList(mostPopItems));
    }

}
