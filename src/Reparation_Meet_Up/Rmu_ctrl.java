package Reparation_Meet_Up;

import java.sql.SQLException;
import java.util.*;
import Reparation.Repa_mod;
import Vehicule.Veh_mod;

public class Rmu_ctrl {
    private static Rmu_DAO RmuDAO;

    public Rmu_ctrl(String host, String user, String password) {

        RmuDAO = new Rmu_DAO(host, user, password);
    }

    public boolean existsByReparationAndVehicle(String numbPlate, String probRepa, String markVeh, String modelVeh,
            int yearVeh, String colorVeh) {
        return RmuDAO.existsByReparationAndVehicle(numbPlate, probRepa, markVeh, modelVeh, yearVeh, colorVeh);

    }

    public List<Rmu_mod> getAllRmu() {
        List<Rmu_mod> rmus = new ArrayList<>();
        try {
            rmus = RmuDAO.getAllRmu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rmus;
    }

    public List<Rmu_mod> getAllRmuTrash() {
        List<Rmu_mod> rmus = new ArrayList<>();
        try {
            rmus = RmuDAO.getAllRmuTrash();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rmus;
    }

    public List<Veh_mod> getAllVehicules() {
        List<Veh_mod> vehicules = new ArrayList<>();
        try {
            vehicules = RmuDAO.getAllVehicules();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicules;
    }

    public List<Repa_mod> getAllReparations() {
        List<Repa_mod> repa = new ArrayList<>();
        try {
            repa = RmuDAO.getAllReparations();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return repa;
    }

    public boolean addRmu(Rmu_mod rmu) {
        try {
            return RmuDAO.addRmu(rmu);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRmu(Rmu_mod rmu) {
        try {
            return RmuDAO.updateRmu(rmu);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteRmu(Rmu_mod rmu) {
        try {
            RmuDAO.deleteRmu(rmu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RestoreRmuTrash(Rmu_mod rmu) {
        try {
            RmuDAO.RestoreRmuTrash(rmu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SureRmuDelete(Rmu_mod rmu) {
        try {
            RmuDAO.SureRmuDelete(rmu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rmu_mod> searchRmu(String npv) {
        List<Rmu_mod> rmus = new ArrayList<>();
        try {
            rmus = RmuDAO.searchRmu(npv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rmus;
    }

    public boolean isReparationAlreadyAssociated(int vehicleId, String probRepa) {
        try {
            return RmuDAO.isReparationAlreadyAssociated(vehicleId, probRepa);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
