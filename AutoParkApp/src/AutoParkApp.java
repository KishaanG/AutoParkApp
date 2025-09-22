import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;

public class AutoParkApp extends Application {

    private AutoPark model;
    private AutoParkView view;

    public AutoParkApp()
    {
        model = AutoPark.createPark();
        view = new AutoParkView(model);
    }

    public void start(Stage primaryStage)
    {

        view.getButtonPane().getAddToCartButton().setDisable(true); // Disables all the buttons
        view.getButtonPane().getRemoveItemButton().setDisable(true);
        view.getButtonPane().getCompleteSaleButton().setDisable(true);

        view.update(); // Updates the view
        view.updatePopItems();

        view.getInvList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.getButtonPane().getAddToCartButton().setDisable(false); // Enables the add to cart button if an Item in the inventory is selected
                view.getButtonPane().getRemoveItemButton().setDisable(true); // Disables the remove item and complete sale button
                view.getButtonPane().getCompleteSaleButton().setDisable(true);
            }
        });

        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.getButtonPane().getRemoveItemButton().setDisable(false);
                if (!model.getCart().isEmpty())
                { // Enables the complete sale button only if the cart isn't empty
                    view.getButtonPane().getCompleteSaleButton().setDisable(false);
                }
                view.getButtonPane().getAddToCartButton().setDisable(true);
            }
        });

        view.getButtonPane().getAddToCartButton().setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent) {
                int index = view.getInvList().getSelectionModel().getSelectedIndex();
                model.getItems()[index].sellUnits(1); // Sells one unit of the item when it's placed in the cart

                if (model.getCart().containsKey(model.getItems()[index]))
                { // Checks if there's a similar car already in the cart
                    model.getCart().put(model.getItems()[index], model.getCart().get(model.getItems()[index])+1);
                    // If there is, increase the integer (which indicates the number of cars) paired with that car
                }
                else
                { // If it's not already in the cart, add it to the map
                    model.getCart().put(model.getItems()[index], 1);
                }
                view.getButtonPane().getAddToCartButton().setDisable(true);
                view.update();
            }
        });

        view.getButtonPane().getRemoveItemButton().setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent) {
                int index = view.getCartList().getSelectionModel().getSelectedIndex();
                Item selectedItem = null;
                int counter = 0;
                for (Item i : model.getCart().keySet())
                { // since cart is a LinkedHashMap it maintains the order of the indices
                    if (counter == index)
                    { // Finds the Item at the selected index
                        selectedItem = i;
                        break;
                    }
                    counter++;
                }

                selectedItem.setInvQuantity(selectedItem.getInvQuantity()+1); // Restores the invQuantity and soldQuantity
                selectedItem.setSoldQuantity(selectedItem.getSoldQuantity()-1);

                if (model.getCart().get(selectedItem) > 1)
                { // Checks if there is more than one of the Item in the cart
                    model.getCart().put(selectedItem, model.getCart().get(selectedItem)-1); // If there is, the number of that Item in the cart is reduced by 1
                }
                else
                { // If there's only one of the Item in the cart
                    model.getCart().remove(selectedItem); // remove the Item from the cart
                }
                view.getButtonPane().getRemoveItemButton().setDisable(true);
                view.update();
            }
        });

        view.getButtonPane().getCompleteSaleButton().setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent) {
                model.setRevenue(model.getRevenue()+model.getCurrentCart()); // Sets the AutoPark's revenue
                for (Integer i : model.getCart().values())
                { // Increases the number of sales by the number of cars in the cart
                    model.setNumSales(model.getNumSales()+i);
                }
                model.getCart().clear(); // Clears the cart
                view.getButtonPane().getRemoveItemButton().setDisable(true);
                view.update();
                view.updatePopItems();
            }
        });

        view.getButtonPane().getResetStockButton().setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent)
            {
                model = AutoPark.createPark(); // Resets the model
                view.update();
                view.updatePopItems();
            }
        });

        Pane aPane = new Pane();
        aPane.getChildren().add(view);

        primaryStage.setTitle(model.getName() + " - Sales and Inventory");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}