package Maintenance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Maint_ctrl {
    private static Maint_DAO Maint_DAO;

    public Maint_ctrl(String host, String user, String password) {

        Maint_DAO = new Maint_DAO(host, user, password);
    }

    public boolean existsByType(String type) {
        return Maint_DAO.existsByType(type);
    }

    public void updateMaintenance(Maint_mod maint) {
        try {
            Maint_DAO.updateMaintenance(maint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsByDescription(String description) {
        return Maint_DAO.existsByDescription(description);
    }

    public List<Maint_mod> getAllMaintenance() {
        List<Maint_mod> maints = new ArrayList<>();
        try {
            maints = Maint_DAO.getAllMaintenance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maints;
    }

    public List<Maint_mod> getAllMaintenanceTrash() {
        List<Maint_mod> maints = new ArrayList<>();
        try {
            maints = Maint_DAO.getAllMaintenanceTrash();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maints;
    }

    public void addMaintenance(Maint_mod maint) {
        try {
            Maint_DAO.addMaintenance(maint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMaintenance(Maint_mod maint) {
        try {
            Maint_DAO.deleteMaintenance(maint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RestoreTrashMaintenance(Maint_mod maint) {
        try {
            Maint_DAO.RestoreTrashMaintenance(maint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SureDeleteMaintenance(Maint_mod maint) {
        try {
            Maint_DAO.SureDeleteMaintenance(maint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Maint_mod> searchType(String type) {
        List<Maint_mod> maints = new ArrayList<>();
        try {
            maints = Maint_DAO.searchType(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maints;
    }

}
