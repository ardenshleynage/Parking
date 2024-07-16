package Maintenance_Meet_Up;

public class Mmu_mod {
    private int id;
    private int id_maint;
    private String type_maint;
    private String description_maint;
    private double price_maint;
    private int id_veh;
    private String numb_plate_vehicle;
    private String mark_veh;
    private String model_veh;
    private String color_veh;
    private int year_veh;
    private String add_date;

    public Mmu_mod() {
    }

    public Mmu_mod(int id, int id_maint, String type_maint, String description_maint, double price_maint, int id_veh,
            String numb_plate_vehicle,
            String mark_veh, String model_veh, String color_veh, int year_veh, String add_date) {
        this.id = id;
        this.id_maint = id_maint;
        this.type_maint = type_maint;
        this.description_maint = description_maint;
        this.price_maint = price_maint;
        this.id_veh = id_veh;
        this.numb_plate_vehicle = numb_plate_vehicle;
        this.mark_veh = mark_veh;
        this.model_veh = model_veh;
        this.color_veh = color_veh;
        this.year_veh = year_veh;
        this.add_date = add_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMaint() {
        return id_maint;
    }

    public void setIdMaint(int id_maint) {
        this.id_maint = id_maint;
    }

    public String getTypeMaint() {
        return type_maint;
    }

    public void setTypeMaint(String type_maint) {
        this.type_maint = type_maint;
    }

    public String getDescriptionMaint() {
        return description_maint;
    }

    public void setDescriptionMaint(String description_maint) {
        this.description_maint = description_maint;
    }

    public double getPriceMaint() {
        return price_maint;
    }

    public void setPriceMaint(double price_maint) {
        this.price_maint = price_maint;
    }

    public int getIdVeh() {
        return id_veh;
    }

    public void setIdVeh(int id_veh) {
        this.id_veh = id_veh;
    }

    public String getNumbPlateVehicle() {
        return numb_plate_vehicle;
    }

    public void setNumbPlateVehicle(String numb_plate_vehicle) {
        this.numb_plate_vehicle = numb_plate_vehicle;
    }

    public String getMarkVeh() {
        return mark_veh;
    }

    public void setMarkVeh(String mark_veh) {
        this.mark_veh = mark_veh;
    }

    public String getModelVeh() {
        return model_veh;
    }

    public void setModelVeh(String model_veh) {
        this.model_veh = model_veh;
    }

    public String getColorVeh() {
        return color_veh;
    }

    public void setColorVeh(String color_maint) {
        this.color_veh = color_maint;
    }

    public int getYearVeh() {
        return year_veh;
    }

    public void setYearVeh(int year_veh) {
        this.year_veh = year_veh;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    /*public String toString() {
        return String.format(
                "MARQUE : %s\nMODÈLE: %s\nANNÉE: %d\nCOULEUR: :  %s\nNOMBRE DE MAINTENANCE : %d\nMAINTENANCE : %s\nPRIX TOTAL MAINTENANCE : %lf\nDATE D'AJOUT: %s",
                numb_plate.toUpperCase(), mark.toUpperCase(), model.toUpperCase(), year, color.toUpperCase(), kilometer,
                add_date);
    } */

}
