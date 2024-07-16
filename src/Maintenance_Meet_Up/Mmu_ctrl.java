package Maintenance_Meet_Up;

import java.util.*;
import Maintenance.Maint_mod;
import java.sql.SQLException;
import Vehicule.Veh_mod;

public class Mmu_ctrl {
    private static Mmu_DAO MmuDAO;

    public Mmu_ctrl(String host, String user, String password) {

        MmuDAO = new Mmu_DAO(host, user, password);
    }

    public boolean existsByMaintenanceAndVehicle(String numbPlate, String typeMaint, String markVeh, String modelVeh,
            int yearVeh, String colorVeh) {
        return MmuDAO.existsByMaintenanceAndVehicle(numbPlate, typeMaint, markVeh, modelVeh, yearVeh, colorVeh);

    }

    public List<Mmu_mod> getAllMmu() {
        List<Mmu_mod> mmus = new ArrayList<>();
        try {
            mmus = MmuDAO.getAllMmu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mmus;
    }

    public List<Mmu_mod> getAllMmuTrash() {
        List<Mmu_mod> mmus = new ArrayList<>();
        try {
            mmus = MmuDAO.getAllMmuTrash();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mmus;
    }

    public List<Veh_mod> getAllVehicules() {
        List<Veh_mod> vehicules = new ArrayList<>();
        try {
            vehicules = MmuDAO.getAllVehicules();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicules;
    }

    public List<Maint_mod> getAllMaintenance() {
        List<Maint_mod> maints = new ArrayList<>();
        try {
            maints = MmuDAO.getAllMaintenance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maints;
    }

    public boolean addMmu(Mmu_mod mmu) {
        try {
            return MmuDAO.addMmu(mmu);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMmu(Mmu_mod mmu) {
        try {
            return MmuDAO.updateMmu(mmu);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteMmu(Mmu_mod mmu) {
        try {
            MmuDAO.deleteMmu(mmu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RestoreMmuTrash(Mmu_mod mmu) {
        try {
            MmuDAO.RestoreMmuTrash(mmu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SureMmuDelete(Mmu_mod mmu) {
        try {
            MmuDAO.SureMmuDelete(mmu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mmu_mod> searchMmu(String npv) {
        List<Mmu_mod> mmus = new ArrayList<>();
        try {
            mmus = MmuDAO.searchMmu(npv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mmus;
    }
    public boolean isMaintenanceAlreadyAssociated(int vehicleId, String typeMaint) {
        try {
            return MmuDAO.isMaintenanceAlreadyAssociated(vehicleId, typeMaint);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
