package Maintenance;

public class Maint_mod {
    private int id;
    private String type;
    private String description;
    private double price;
    private String add_date;

    public Maint_mod() {
    }

    public Maint_mod(int id, String type, String description, double price, String add_date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.add_date = add_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String toString() {
        return String.format(
                "TYPE: %s\nDESCRIPTION: %s\nPRIX: %s G\nDATE D'AJOUT: %s",
                type.toUpperCase(), description.toUpperCase(), price, add_date);
    }
}
