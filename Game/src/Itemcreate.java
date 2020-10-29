public class Itemcreate {



    private String itemName;
    private String itemDesc;

    public Itemcreate(String itemName, String itemDesc) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }

    //getter

    public String getItemName() {
        return itemName;
    }
    public String getItemDesc() {
        return itemDesc;
    }

    //setter
    public void setItemNameDesc(String itemName, String itemDesc) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }
}

