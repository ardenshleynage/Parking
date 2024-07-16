package Reparation_Meet_Up;

import java.sql.*;
import java.util.*;
import Reparation.Repa_mod;
import Vehicule.Veh_mod;

public class Rmu_DAO {
    private String URL = "jdbc:mysql://localhost:3306/parking";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Rmu_DAO(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

    }

    protected void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    protected void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public boolean existsByReparationAndVehicle(String numbPlate, String probRep, String markVeh, String modelVeh,
            int yearVeh, String colorVeh) {
        String query = "SELECT COUNT(*) FROM reparations_meet_up WHERE numb_plate_vehicle = ? AND problem_reparation = ? AND mark_vehicle = ? AND model_vehicle = ? AND year_vehicle = ? AND color_vehicle = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, numbPlate);
            preparedStatement.setString(2, probRep);
            preparedStatement.setString(3, markVeh);
            preparedStatement.setString(4, modelVeh);
            preparedStatement.setInt(5, yearVeh);
            preparedStatement.setString(6, colorVeh);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Veh_mod> getAllVehicules() throws SQLException {
        String sql = "SELECT * FROM Vehicule WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Veh_mod> vehicules = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String numb_plate = rs.getString("numb_plate");
            String mark = rs.getString("mark");
            String model = rs.getString("model");
            int year = rs.getInt("year");
            String color = rs.getString("color");
            int kilometer = rs.getInt("kilometer");
            String add_date = rs.getString("add_date");

            Veh_mod vehicule = new Veh_mod(id, numb_plate, mark, model, year, color, kilometer, add_date);
            vehicules.add(vehicule);
        }

        rs.close();
        pst.close();

        return vehicules;
    }

    public List<Repa_mod> getAllReparations() throws SQLException {
        String sql = "SELECT * FROM reparations_list WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Repa_mod> repas = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String type = rs.getString("problem");
            String description = rs.getString("solution");
            double price = rs.getDouble("price");
            String add_date = rs.getString("add_date");

            Repa_mod repa = new Repa_mod(id, type, description, price, add_date);
            repas.add(repa);
        }

        rs.close();
        pst.close();

        return repas;
    }

    public List<Rmu_mod> getAllRmu() throws SQLException {
        String sql = "SELECT * FROM reparations_meet_up WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Rmu_mod> rmus = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int id_repa = rs.getInt("id_reparation");
            String prob_repa = rs.getString("problem_reparation");
            String sol_repa = rs.getString("solution_reparation");
            double price_repa = rs.getDouble("price_reparation");
            int id_veh = rs.getInt("id_vehicle");
            String numb_plate_vehicle = rs.getString("numb_plate_vehicle");
            String mark_veh = rs.getString("mark_vehicle");
            String model_veh = rs.getString("model_vehicle");
            String color_veh = rs.getString("color_vehicle");
            int year_veh = rs.getInt("year_vehicle");
            String add_date = rs.getString("add_date");

            Rmu_mod rmu = new Rmu_mod(id, id_repa, prob_repa, sol_repa, price_repa, id_veh,
                    numb_plate_vehicle, mark_veh, model_veh, color_veh, year_veh, add_date);
            rmus.add(rmu);
        }

        rs.close();
        pst.close();

        return rmus;
    }

    public List<Rmu_mod> getAllRmuTrash() throws SQLException {
        String sql = "SELECT * FROM reparations_meet_up WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Rmu_mod> rmus = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int id_repa = rs.getInt("id_reparation");
            String prob_repa = rs.getString("problem_reparation");
            String sol_repa = rs.getString("solution_reparation");
            double price_repa = rs.getDouble("price_reparation");
            int id_veh = rs.getInt("id_vehicle");
            String numb_plate_vehicle = rs.getString("numb_plate_vehicle");
            String mark_veh = rs.getString("mark_vehicle");
            String model_veh = rs.getString("model_vehicle");
            String color_veh = rs.getString("color_vehicle");
            int year_veh = rs.getInt("year_vehicle");
            String add_date = rs.getString("add_date");

            Rmu_mod rmu = new Rmu_mod(id, id_repa, prob_repa, sol_repa, price_repa, id_veh,
                    numb_plate_vehicle, mark_veh, model_veh, color_veh, year_veh, add_date);
            rmus.add(rmu);
        }

        rs.close();
        pst.close();

        return rmus;
    }

    public boolean addRmu(Rmu_mod rmu) throws SQLException {
        String sql = "INSERT INTO reparations_meet_up (id_reparation, problem_reparation, solution_reparation, price_reparation, id_vehicle, numb_plate_vehicle, mark_vehicle, model_vehicle, color_vehicle, year_vehicle, access, add_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, rmu.getIdRepa());
        statement.setString(2, rmu.getProbRepa());
        statement.setString(3, rmu.getSolRepa());
        statement.setDouble(4, rmu.getPriceRepa());
        statement.setInt(5, rmu.getIdVeh());
        statement.setString(6, rmu.getNumbPlateVehicle());
        statement.setString(7, rmu.getMarkVeh());
        statement.setString(8, rmu.getModelVeh());
        statement.setString(9, rmu.getColorVeh());
        statement.setInt(10, rmu.getYearVeh());
        statement.setInt(11, 1);
        statement.setString(12, rmu.getAdd_date());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean updateRmu(Rmu_mod rmu) throws SQLException {
        String sql = "UPDATE reparations_meet_up SET id_reparation = ?, problem_reparation = ?, solution_reparation = ?, price_reparation = ?, id_vehicle = ?, numb_plate_vehicle= ?, mark_vehicle = ?, model_vehicle = ?, color_vehicle = ?, year_vehicle = ?, add_date = ? WHERE id = ?";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, rmu.getIdRepa());
        statement.setString(2, rmu.getProbRepa());
        statement.setString(3, rmu.getSolRepa());
        statement.setDouble(4, rmu.getPriceRepa());
        statement.setInt(5, rmu.getIdVeh());
        statement.setString(6, rmu.getNumbPlateVehicle());
        statement.setString(7, rmu.getMarkVeh());
        statement.setString(8, rmu.getModelVeh());
        statement.setString(9, rmu.getColorVeh());
        statement.setInt(10, rmu.getYearVeh());
        statement.setString(11, rmu.getAdd_date());
        statement.setInt(12, rmu.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteRmu(Rmu_mod rmu) throws SQLException {
        String sql = "UPDATE reparations_meet_up SET access = ? WHERE id = ?";
        connect();

        int id = rmu.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean RestoreRmuTrash(Rmu_mod rmu) throws SQLException {
        String sql = "UPDATE reparations_meet_up SET access = ? WHERE id = ?";
        connect();

        int id = rmu.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean SureRmuDelete(Rmu_mod rmu) throws SQLException {

        String sql = "DELETE FROM reparations_meet_up WHERE id = ?";
        connect();

        int id = rmu.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public List<Rmu_mod> searchRmu(String npv) throws SQLException {
        List<Rmu_mod> rmus = new ArrayList<>();
        String checkAccessSql = "SELECT access FROM vehicule WHERE numb_plate = ? AND access = 1";
        String fetchRmuSql = "SELECT * FROM reparations_meet_up WHERE numb_plate_vehicle = ? AND access = 1";

        connect();

        try {
            boolean accessAllowed = false;

            // Check access in reparations_list
            try (PreparedStatement checkAccessStmt = con.prepareStatement(checkAccessSql)) {
                checkAccessStmt.setString(1, npv);
                try (ResultSet accessResultSet = checkAccessStmt.executeQuery()) {
                    if (accessResultSet.next()) {
                        accessAllowed = true;
                    }
                }
            }

            if (accessAllowed) {
                // Fetch data from reparations_meet_up
                try (PreparedStatement fetchRmuStmt = con.prepareStatement(fetchRmuSql)) {
                    fetchRmuStmt.setString(1, npv);
                    try (ResultSet resultSet = fetchRmuStmt.executeQuery()) {
                        while (resultSet.next()) {
                            String prob_repa = resultSet.getString("problem_reparation");
                            String sol_repa = resultSet.getString("solution_reparation");
                            double price_repa = resultSet.getDouble("price_reparation");
                            String numb_plate_vehicle = resultSet.getString("numb_plate_vehicle");
                            String mark_veh = resultSet.getString("mark_vehicle");
                            String model_veh = resultSet.getString("model_vehicle");
                            String color_veh = resultSet.getString("color_vehicle");
                            int year_veh = resultSet.getInt("year_vehicle");
                            String add_date = resultSet.getString("add_date");

                            Rmu_mod rmu = new Rmu_mod(0, 0, prob_repa, sol_repa, price_repa, 0, numb_plate_vehicle,
                                    mark_veh, model_veh, color_veh, year_veh, add_date);
                            rmus.add(rmu);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return rmus;
    }

    public boolean isReparationAlreadyAssociated(int vehicleId, String probRepa) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reparations_meet_up WHERE id_vehicle = ? AND problem_reparation = ?";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, vehicleId);
        statement.setString(2, probRepa);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        boolean exists = resultSet.getInt(1) > 0;

        resultSet.close();
        statement.close();
        disconnect();
        return exists;
    }
}
