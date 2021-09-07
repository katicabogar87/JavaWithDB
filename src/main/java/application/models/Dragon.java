package application.models;


import java.util.List;

/**CREATE TABLE dragon (
        id INT UNSIGNED AUTO_INCREMENT,
        unique_name VARCHAR(100) UNIQUE,
        dragon_text LONGTEXT,
        rarity ENUM('common','rare','very rare','epic','legendary','heroic'),
        design LONGBLOB,
        PRIMARY KEY (id)
        );*/

public class Dragon {

    long id;
    String uniqueName;
    String dragonText;
    Rarity rarity;
    private byte[] design;

    private List<Element> elements;

    public Dragon(){}

    public Dragon(long id, String uniqueName, String dragonText, Rarity rarity) {
        this.id = id;
        this.uniqueName = uniqueName;
        this.dragonText = dragonText;
        this.rarity = rarity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getDragonText() {
        return dragonText;
    }

    public void setDragonText(String dragonText) {
        this.dragonText = dragonText;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public byte[] getDesign() {
        return design;
    }

    public void setDesign(byte[] design) {
        this.design = design;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return id + " - " +
                uniqueName + ", " +
                dragonText + ", " +
                rarity + "\n\t"+
                elements;
    }
}
