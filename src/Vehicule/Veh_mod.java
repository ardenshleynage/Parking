package Vehicule;

public class Veh_mod {
    private int id;
    private String numb_plate;
    private String mark;
    private String model;
    private int year;
    private String color;
    private int kilometer;
    private String add_date;

    public Veh_mod() {
    }

    public Veh_mod(int id, String numb_plate, String mark, String model, int year, String color, int kilometer,
            String add_date) {
        this.id = id;
        this.numb_plate = numb_plate;
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.color = color;
        this.kilometer = kilometer;
        this.add_date = add_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumb_plate() {
        return numb_plate;
    }

    public void setNumb_plate(String numb_plate) {
        this.numb_plate = numb_plate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String toString() {
        return String.format(
                "IMMATRICULATION: %s\nMARQUE: %s\nMODÈLE: %s\nANNÉE: %d\nCOULEUR: %s\nKILOMÈTRES: %d\nDATE D'AJOUT: %s",
                numb_plate.toUpperCase(),mark.toUpperCase(), model.toUpperCase(), year, color.toUpperCase(), kilometer, add_date);
    }

}
