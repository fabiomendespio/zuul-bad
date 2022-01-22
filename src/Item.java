import java.util.ArrayList;

public class Item {

    private String itemDescription;
    private int itemWeight;

    public Item (String itemDescription, int itemWeight){
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }

    public String itemDescriptionSttring() {
            return "[" +itemDescription + " peso: " + itemWeight + "] ";
    }
}
