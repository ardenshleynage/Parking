package Reparation_Meet_Up;

public class Rmu_mod {
    private int id;
    private int id_repa;
    private String prob_repa;
    private String sol_repa;
    private double price_repa;
    private int id_veh;
    private String numb_plate_vehicle;
    private String mark_veh;
    private String model_veh;
    private String color_veh;
    private int year_veh;
    private String add_date;

    public Rmu_mod() {
    }

    public Rmu_mod(int id, int id_repa, String prob_repa, String sol_repa, double price_repa, int id_veh,
            String numb_plate_vehicle,
            String mark_veh, String model_veh, String color_veh, int year_veh, String add_date) {
        this.id = id;
        this.id_repa = id_repa;
        this.prob_repa = prob_repa;
        this.sol_repa = sol_repa;
        this.price_repa = price_repa;
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

    public int getIdRepa() {
        return id_repa;
    }

    public void setIdRepa(int id_repa) {
        this.id_repa = id_repa;
    }

    public String getProbRepa() {
        return prob_repa;
    }

    public void setProbRepa(String prob_repa) {
        this.prob_repa = prob_repa;
    }

    public String getSolRepa() {
        return sol_repa;
    }

    public void setSolRepa(String sol_repa) {
        this.sol_repa = sol_repa;
    }

    public double getPriceRepa() {
        return price_repa;
    }

    public void setPriceRepa(double price_repa) {
        this.price_repa = price_repa;
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

}
