package Reparation;

import java.sql.SQLException;
import java.util.*;

public class Repa_ctrl {
    private static Repa_DAO Repa_DAO;

    public Repa_ctrl(String host, String user, String password) {

        Repa_DAO = new Repa_DAO(host, user, password);
    }

    public boolean existsByProblem(String problem) {
        return Repa_DAO.existsByProblem(problem);
    }

    public void updateReparations(Repa_mod rep) {
        try {
            Repa_DAO.updateMaintenance(rep);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsBySolution(String solution) {
        return Repa_DAO.existsBySolution(solution);
    }

    public List<Repa_mod> getAllReparations() {
        List<Repa_mod> repa = new ArrayList<>();
        try {
            repa = Repa_DAO.getAllReparations();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return repa;
    }

    public List<Repa_mod> getAllReparationsTrash() {
        List<Repa_mod> repa = new ArrayList<>();
        try {
            repa = Repa_DAO.getAllReparationsTrash();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return repa;
    }

    public void addReparations(Repa_mod rep) {
        try {
            Repa_DAO.addReparations(rep);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReparations(Repa_mod rep) {
        try {
            Repa_DAO.deleteReparations(rep);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RestoreTrashReparations(Repa_mod rep) {
        try {
            Repa_DAO.RestoreTrashReparations(rep);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SureDeleteReparations(Repa_mod repa) {
        try {
            Repa_DAO.SureDeleteReparations(repa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Repa_mod> searchProblem(String problem) {
        List<Repa_mod> repa = new ArrayList<>();
        try {
            repa = Repa_DAO.searchProblem(problem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repa;
    }

}
