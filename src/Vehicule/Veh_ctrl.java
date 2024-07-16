package Vehicule;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public class Veh_ctrl {
    private static VehiculeDAO vehiculeDAO;
  

    public Veh_ctrl(String host, String user, String password) {
    
        vehiculeDAO = new VehiculeDAO(host, user, password);
    }

    public List<Veh_mod> getAllVehicules() {
        List<Veh_mod> vehicules = new ArrayList<>();
        try {
            vehicules = vehiculeDAO.getAllVehicules();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return vehicules;
    }
    public List<Veh_mod> getAllVehiculesTrash() {
        List<Veh_mod> vehicules = new ArrayList<>();
        try {
            vehicules = vehiculeDAO.getAllVehiculesTrash();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return vehicules;
    }
    
    public void addVehicule(Veh_mod vehicule) {
        try {
            vehiculeDAO.addVehicule(vehicule);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean existsByNumbPlate(String numbPlate) {
        return vehiculeDAO.existsByNumbPlate(numbPlate);
    }

    public void updateVehicule(Veh_mod vehicule) {
        try {
            vehiculeDAO.updateVehicule(vehicule);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void deleteVehicule(Veh_mod vehicule) {
        try {
            vehiculeDAO.deleteVehicule(vehicule);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void RestoreTrash(Veh_mod vehicule) {
        try {
            vehiculeDAO.RestoreTrash(vehicule);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void SureDelete(Veh_mod vehicule) {
        try {
            vehiculeDAO.SureDelete(vehicule);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Veh_mod> searchVehiclesByModel(String model) {
        List<Veh_mod> vehicules = new ArrayList<>();
        try {
            vehicules= vehiculeDAO.searchByModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicules;
    }
    
    public List<Veh_mod> searchVehiclesByPlate(String numbPlate) {
        List<Veh_mod> vehicules = new ArrayList<>();
        try {
            vehicules=vehiculeDAO.searchByNumbPlate(numbPlate);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return vehicules;
    }
    
}
