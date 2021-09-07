package application.models;

public class Element {
    String elementName;
    private byte[] icon_img;

    public Element(String elementName) {
        this.elementName = elementName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public byte[] getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(byte[] icon_img) {
        this.icon_img = icon_img;
    }

    public Element(String elementName, byte[] icon_img) {
        this.elementName = elementName;
    }

    @Override
    public String toString() {
        return elementName;
    }
}
