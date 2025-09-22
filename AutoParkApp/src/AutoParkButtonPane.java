import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AutoParkButtonPane extends Pane {
    Button addToCartButton;
    Button removeItemButton;
    Button completeSaleButton;
    Button resetStockButton;

    public AutoParkButtonPane()
    {
        addToCartButton = new Button("Add to Cart");
        addToCartButton.relocate(0,0);

        removeItemButton = new Button ("Remove Item");
        removeItemButton.relocate(200, 0);

        completeSaleButton = new Button("Complete Sale");
        completeSaleButton.relocate(300, 0);

        resetStockButton = new Button("Reset Stock");
        resetStockButton.relocate(510, 0);
        this.getChildren().addAll(addToCartButton, removeItemButton, completeSaleButton, resetStockButton);
    }

    public Button getAddToCartButton()
    {
        return addToCartButton;
    }
    public Button getRemoveItemButton()
    {
        return removeItemButton;
    }
    public Button getCompleteSaleButton()
    {
        return completeSaleButton;
    }
    public Button getResetStockButton()
    {
        return resetStockButton;
    }
}
